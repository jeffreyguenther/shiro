tree grammar GraphBuilderPass;

options{
  output=AST;
  tokenVocab=Shiro;
  ASTLabelType=CommonTree;
}

@header{
package shiro.interpreter;

import java.util.Set;
import java.util.LinkedHashSet;

import shiro.Port;
import shiro.Node;
import shiro.Scope;
import shiro.SubjunctiveParametricSystem;
import shiro.dag.DependencyRelation;
import shiro.expressions.*;
import shiro.PathNotFoundException;
}

@members{
SubjunctiveParametricSystem pSystem;
List<DependencyRelation<Port>> deps;
}

shiro [SubjunctiveParametricSystem ps]  
@init{
pSystem = ps;
// initalize the list of dependencies
deps = new ArrayList<DependencyRelation<Port>>();

}
  :   statement+
  ; 
  
statement
  : nodestmt
  | sNode
  | graphDecl
  | statestmt
  | collection
  | view
  ;
  
view  : ^('view' IDENT mfName IDENT)
  ;
  
collection
  : ^('collection' IDENT orderingFunc path[null] collItem+)
  ;

collItem: IDENT
  ;

orderingFunc
  : IDENT
  ;
  
statestmt
  : ^(STATE_DECL stateHeader)
  ;
  
stateHeader
  :   (stateTimeStmt | stateCommentStmt | stateParentStmt| stateGraphStmt |activation )+  
  ;
  
stateTimeStmt
  : ^('Time' time)
  ;

stateCommentStmt
  : ^('Comment' comment)  
  ;
  
stateParentStmt
  : ^('Parent' stateParent)
  ;
  
stateGraphStmt
  : ^('Graph' stateGraph)
  ;
  
stateName
  : IDENT
  ;
  
time  : STRING_LITERAL  
  ;
  
comment : STRING_LITERAL
  ;
  
stateParent
  : IDENT
  ;
  
stateGraph
  : IDENT
  ;

activationPath returns [String symbolName, String activePort]
  : l=activation ('.'^ (r=activation | activationList))* 
{
$symbolName = l.symbolName;
$activePort = l.activePort;
}
  ;
  
activationList
  : ^(ACTIVATION_LIST activation+)
  ;
  
activation returns [String symbolName, String activePort]
  : ^(ACTIVATION i1=IDENT 
          {$symbolName = $i1.text;}
            (i2=IDENT {$activePort = $i2.text;})?)
  ;
  
graphDecl
@Before{
System.out.println("Creating graph");
}
  : ^('graph' IDENT graphLine+)
{

Set<Port> ports = new LinkedHashSet<Port>();
// for each node generated in the graph generation process
for(Node n: pSystem.getNodes()){
  // get all of the dependencies for each node
  deps.addAll(n.getDependencies());
  ports.addAll(n.getPorts());
}

// resolve the dependencies between ports as indicated by expressions
// by adding dependencies between the two ports to the graph
System.out.println();
System.out.println("Dependencies");
for(DependencyRelation<Port> d: deps){
  System.out.println(d.getDependent().getFullName() + " -> " + d.getDependedOn().getFullName());
  pSystem.addDependency(d);
}

}
  ;
  
graphLine
  : nodeProduction 
  | portAssignment
  ;
  
nodeInternal
  : (nodeProduction | portAssignment | portstmt | nodestmt | sNode | NEWLINE!)+
  ;
  
nodestmt 
  : ^(NODE_STMT IDENT activeSelector? nodeInternal)
  ;
  
sNode
  : 'subjunctive node'^ IDENT '['! subjunctSelector ']'! 'begin'! NEWLINE!
    (subjunctDeclNodeProd | subjunctDecl | NEWLINE!)+
    'end'!
  ;
  
subjunctDeclNodeProd
  : ^(SUBJ_NODE_PROD IDENT IDENT nodeInternal )
  ;
  
subjunctDecl
  : 'subjunct'^ IDENT ('['! activeSelector ']'!)? 'begin'! NEWLINE!
    nodeInternal
    'end'!
  ;

subjunctSelector
  : ^(SUBJ_SELECT IDENT)
  ;
  
activeSelector  
  : ^(EVAL_SELECT IDENT)
  ;

nodeProduction returns [Path pathToReturn]
  : ^('->' path[null]  activationPath)
{
//create a new node from the AST with the given name
Node producedNode = pSystem.produceNodeFromPath($path.p.getPathAsString(), $activationPath.symbolName);

	if(producedNode != null){
	//TODO: if the produced node is not added to the subjuncitve system object, add it.
	//activate the port for the produced node using activationPath.activatedPort
	
	  
	}else{
	
	}
	$pathToReturn = $path.p;
System.out.println("Node declared: " 
	+ producedNode.getFullName() + " with active update " 
	+ producedNode.getSelectedEvaluatedPort());
System.out.println();
}
  |  ^('->' l=nodeProduction activationPath)
{
Node producedNode = pSystem.produceNodeFromPath($l.pathToReturn.getPathAsString(), $activationPath.symbolName);

  if(producedNode != null){
  //TODO: if the produced node is not added to the subjuncitve system object, add it.
  //activate the port for the produced node using activationPath.activatedPort
  
    
  }else{
  
  }
  $pathToReturn = $l.pathToReturn;
System.out.println("Node declared: " 
	+ producedNode.getFullName() + " with active update " 
	+ producedNode.getSelectedEvaluatedPort());
System.out.println();
} 
  ;

portAssignment
@init{
Node n;
}	
  : ^(PORT_ASSIGNMENT path[null]
{
// look up the node object, so that it can be used as the scope object for the mf's expression's parameters.
n = pSystem.getNode($path.p);
} 
	mfparams[n])
{
// look up port based on the path
try{
  Port p = (Port) pSystem.resolvePath($path.p);
  // set the port's expression
  p.setArguments($mfparams.expressions);
  System.out.println(p + " args were set."); 
  System.out.println("Node is now:\n" + n); 
}catch (PathNotFoundException pnfe){
  System.out.println(pnfe);
}
}
  ;

// Port Statement
portDecl
  : ^(PORT_DECL ^(PORT_TAG portType) portName mfName)
  ;
  
portDeclInit
  : ^(PORT_INIT ^(PORT_TAG portType) portName mfCall[null])
  ;

portstmt  
  : portDecl 
  | portDeclInit
  ; 
  
portName 
  : IDENT
  ;
  
portType: 'port'
  |   'eval'
  ;
  
mfCall[Scope sc] returns [String name, int line, int pos, List<Expression> expressions]
  : ^(mfName mfparams[sc]) 
{$name = $mfName.name;
$line = $mfName.line;
$pos = $mfName.pos;
$expressions = $mfparams.expressions;
}
  ;
  
mfName  returns [String name, int line, int pos]
  : i=IDENT { $name = $i.text; $line = $i.line; $pos = $i.pos; }
  ;

mfparams[Scope sc] returns [List<Expression> expressions]
@init{
$expressions = new ArrayList<Expression>();
} 
  : (exps=expression[sc] {$expressions.add($exps.exp);})+
  ;
  
path[Scope sc] returns [Path p]
@init{
List<String> parts = new ArrayList<String>();
boolean hasPathIndex = false;
}
  : ^(PATH (id=IDENT{ parts.add($id.text); })+ (pathIndex{ hasPathIndex = true; })? )
{
if(hasPathIndex){
  if ($pathIndex.index >=0 ){
    retval.p = new Path($sc, parts, $pathIndex.index);
  }else if($pathIndex.pathKey != ""){
    retval.p = new Path($sc, parts, $pathIndex.pathKey);
  }
}else{
  retval.p = new Path($sc, parts);
}
}
  ;
  
pathIndex returns [String pathKey, int index]
  :^(PORT_INDEX portIndex)
{
$pathKey = $portIndex.pathKey;
$index = $portIndex.index;
}
  ;
  
portIndex returns [String pathKey, int index]
@init{
$index = -1;
$pathKey = "";
}
  : ( NUMBER {$index = Integer.parseInt($NUMBER.text);}
    |STRING_LITERAL {$pathKey = $STRING_LITERAL.text.replace("\"","");}
    )
  ;
  
// Expressions
expression[Scope sc] returns [Expression exp]
  : ^('+' op1=expression[sc] op2=expression[sc]) {$exp = new Add($op1.exp, $op2.exp);}
  | ^('-' op1=expression[sc] op2=expression[sc]) {$exp = new Subtract($op1.exp, $op2.exp);}
  | ^('*' op1=expression[sc] op2=expression[sc]) {$exp = new Multiply($op1.exp, $op2.exp);}
  | ^('/' op1=expression[sc] op2=expression[sc]) {$exp = new Divide($op1.exp, $op2.exp);}
  | ^('%' op1=expression[sc] op2=expression[sc]) {$exp = new Mod($op1.exp, $op2.exp);}
  | ^('|' op1=expression[sc] op2=expression[sc]) {$exp = new Or($op1.exp, $op2.exp);}
  | NUMBER {$exp = new shiro.expressions.Number(Float.parseFloat($NUMBER.text));}
  | STRING_LITERAL {{$exp = new SString($STRING_LITERAL.text.replace("\"",""));}}
  |   path[sc] {$exp = $path.p;}
  ;