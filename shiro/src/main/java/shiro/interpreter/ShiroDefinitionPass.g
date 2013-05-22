tree grammar ShiroDefinitionPass;

options{
	tokenVocab=Shiro;
	ASTLabelType=CommonTree;
}

@header{
package shiro.interpreter;

import java.util.Set;
import shiro.SubjunctiveNode;
import shiro.Port;
import shiro.Node;
import shiro.PortType;
import shiro.Scope;
import shiro.functions.MultiFunction;
import shiro.expressions.*;
import shiro.SubjunctiveParametricSystem;
}

@members{

// Declare Graph object for manipulating dependency graph
private SubjunctiveParametricSystem pSystem;
private Node nodeToReturn = null;

public Node getCreatedNode(){
return nodeToReturn;
}
}

shiro[SubjunctiveParametricSystem ps] returns [Node node]
@init{
	pSystem = ps; 
}
	: 	statement
	;	
	
statement	
	:	nodestmt[null, pSystem] {nodeToReturn = $nodestmt.n;} // pass in null for the containing node, and the pSystem as the containing scope
	| 	sNode[null, pSystem]    // pass in null for the containing node, and the pSystem as the containing scope
	|	graphDecl
	|	statestmt
	|	collection
	|	view
	;

view	:	^('view' IDENT mfName IDENT)
	;
	
collection
	:	^('collection' IDENT orderingFunc path[null] collItem+)
	;

collItem:	IDENT
	;

orderingFunc
	:	IDENT
	;
	
statestmt
	:	^(STATE_DECL stateHeader)
	;
	
stateHeader
	: 	(stateTimeStmt | stateCommentStmt | stateParentStmt| stateGraphStmt | activation )+ 	
	;
	
stateTimeStmt
	:	^('Time' time)
	;

stateCommentStmt
	:	^('Comment' comment)	
	;
	
stateParentStmt
	:	^('Parent' stateParent)
	;
	
stateGraphStmt
	:	^('Graph' stateGraph)
	;
	
stateName
	:	IDENT
	;
	
time	:	STRING_LITERAL	
	;
	
comment	:	STRING_LITERAL
	;
	
stateParent
	:	IDENT
	;
	
stateGraph
	:	IDENT
	;
	
activationPath
	:	l=activation ('.' (r=activation | activationList))*
	;

activationList
	:	^(ACTIVATION_LIST activation+)
	;
	
activation
	:	^(ACTIVATION IDENT IDENT?)
	;

// Graph Declarations
graphDecl
	:	^('graph' IDENT graphLine+)
	;
	
graphLine
	:	nodeProduction // pass null to prevent errors when the grammar executes these rules
	| 	portAssignment[null]
	;
// End Graph declaration

nodeInternal[Node n, Scope sc]
	:	(nodeProduction  // pass null to prevent errors when the grammar executes this rule
		|portAssignment [null] // pass null to prevent errors when the grammar executes this rule
		|portstmt[n]	       // create a port, with the n, being the containing node
		|nodestmt[n, sc]       // create a nested node. n is the containing node. sc is the containing scope
		|sNode[n, sc])+        // create a nested subjunctive node
	;

nodestmt [Node containerNode, Scope sc] returns [Node n]
	:	^(NODE_STMT IDENT 
{

// if the node being created is contained by another node
if(containerNode != null){
	// create a new node
	n = new Node($IDENT.text, sc);
	// add the node as a nested node
	containerNode.addNestedContainer(n);
	
}else{
	n = new Node($IDENT.text, sc);
}
}	
	 activeSelector? nodeInternal[n, n])	
{
// set the active evalauated port. 
// This depends on nodeInternal adding ports and nodes before hand.
Set<Port> evalPorts = n.getEvaluatedPorts();

// find the active port and activate it in the node
if(evalPorts.size() > 1) {
	for(Port p: evalPorts){
		if(p.getName().equals($activeSelector.activeUpdate)){
			try{
				n.activate(p.getName());
			}catch (Exception epnf){
				System.out.println($activeSelector.line + ":" + $activeSelector.pos
					+ " " + $activeSelector.activeUpdate
					+ " is not defined.");
				System.err.println(epnf);
			}	
		}
	}
}else{ // activate the only evaluated port in the node
	try{
		Port p = (Port) evalPorts.toArray()[0];
		n.activate(p.getName());
	}catch (Exception epnf){
		System.out.println($activeSelector.line + ":" + $activeSelector.pos
			+ " " + $activeSelector.activeUpdate
			+ " is not defined.");
		System.err.println(epnf);
	}
}
}
	;
	
sNode [Node containerNode, Scope sc] returns [SubjunctiveNode n]	
	:	^('subjunctive node' IDENT subjunctSelector (subjunctDeclNodeProd[containerNode, sc] | subjunctDecl[containerNode, sc])*)
{
// create a subjunctive node
// set the name and the full path of the node
// add the enclosed nodes
}
	;
	
subjunctDeclNodeProd [Node containerNode, Scope sc]
	:	^(SUBJ_NODE_PROD parent=IDENT child=IDENT nodeInternal[containerNode, sc] )
{
// duplicate the node given
// add the children nodes.
}
	;
	
subjunctDecl [Node containerNode, Scope sc]
	:	^('subjunct' name=IDENT activeSelector? nodeInternal[containerNode, sc] )
{
// create a node with the given name
// set the node's full path
// if an active update method is given, use it.
// add the children nodes
}
	;

subjunctSelector returns[String selectedName]
	:	^(SUBJ_SELECT IDENT {selectedName = $IDENT.text;})
	;
	
activeSelector returns [String activeUpdate, int line, int pos]
	:	^(EVAL_SELECT i=IDENT) {$line = $IDENT.line; $pos = $IDENT.pos;} 
{
// add field for getting the name of the port
$activeUpdate = $i.text;
}
	;

nodeProduction
	: 	^('->' path[null]  activationPath)
  	|  	^('->' l=nodeProduction activationPath)
  ;

portAssignment[Node n]
	:	^(PORT_ASSIGNMENT path[n] mfparams[n])	
	;	

// Port Statement
portDecl[Node n] returns [Port p]
	:	^(PORT_DECL ^(PORT_TAG portType) portName mfName) 
{
// create a new port
MultiFunction mf = pSystem.getFunction($mfName.name);

//. detect if the multifunction exists
if(mf != null){
	// create the port
	String path = n.getFullName() + "." + $portName.name;
	// create the port
	p = new Port(path, mf);
}else{
	System.out.println($mfName.line + ":" + $mfName.pos + " Unknown multifunction: " + $mfName.name);
}

if($portType.portType.equals("eval")){
	p.setPortType(PortType.Evaluated);
}
}
	;
	
portDeclInit[Node n] returns [Port p]
	:	^(PORT_INIT ^(PORT_TAG portType) portName mfCall[n])
{
// create a new port
MultiFunction mf = pSystem.getFunction($mfCall.name);

// detect if the multifunction exists
if(mf != null){
	// create a path based on the node's name
	String path = n.getFullName() + "." + $portName.name;
	// create the port
	$p = new Port(path, $mfCall.expressions, mf);
}else{
	System.out.println($mfCall.line + ":" + $mfCall.pos + " Unknown multifunction: " + $mfCall.name);
}

if($portType.portType.equals("eval")){
	$p.setPortType(PortType.Evaluated);
}
}


	;

portstmt[Node n]	
	:	(portDecl[n]
{
n.addPort($portDecl.p);
} 
	|	 portDeclInit[n]
{

if($portDeclInit.p.getPortType() == PortType.Evaluated){
	n.addEvaluatedPort($portDeclInit.p);
}else{
	n.addPort($portDeclInit.p);
}
}
	)
	;	
	
portName returns [String name]
	:	i=IDENT {$name = $i.text;}
	;
	
portType returns [String portType]
	:	s='port' {$portType = $s.text;} | s='eval' {$portType = $s.text;}
	;
	
	
mfCall[Scope sc] returns [String name, int line, int pos, List<Expression> expressions]
	:	^(mfName mfparams[sc]) 
{$name = $mfName.name;
$line = $mfName.line;
$pos = $mfName.pos;
$expressions = $mfparams.expressions;
}
	;
	
mfName 	returns [String name, int line, int pos]
	:	i=IDENT {$name = $i.text; $line = $i.line; $pos = $i.pos;}
	;

mfparams[Scope sc] returns [List<Expression> expressions]
@init{
$expressions = new ArrayList<Expression>();
}	
	:	(exps=expression[sc] {$expressions.add($exps.exp);})+
	;	
	
// Path
path[Scope sc] returns [Path p]
@init{
List<String> parts = new ArrayList<String>();
boolean hasPathIndex = false;
}
	:	^(PATH (id=IDENT{parts.add($id.text);})+ (pathIndex{ hasPathIndex = true;})? )
{
if(hasPathIndex){
	if ($pathIndex.index >=0 ){
		p = new Path($sc, parts, $pathIndex.index);
	}else if($pathIndex.key != ""){
		p = new Path($sc, parts, $pathIndex.key);
	}
}else{
	p = new Path($sc, parts);
}
}
	;
	
pathIndex returns [String key, int index]
	:	^(PORT_INDEX portIndex)
{
$key = $portIndex.key;
$index = $portIndex.index;
}
	;
	
portIndex returns [String key, int index]
@init{
$index = -1;
$key = "";
}
	:	( NUMBER {$index = Integer.parseInt($NUMBER.text);}
		|STRING_LITERAL {{$key = $STRING_LITERAL.text.replace("\"","");}} // remove the quotes
		)
	;
	
// Expressions
expression[Scope sc] returns [Expression exp]
	:	^('+' op1=expression[sc] op2=expression[sc]) {$exp = new Add($op1.exp, $op2.exp);}
	|	^('-' op1=expression[sc] op2=expression[sc]) {$exp = new Subtract($op1.exp, $op2.exp);}
	|	^('*' op1=expression[sc] op2=expression[sc]) {$exp = new Multiply($op1.exp, $op2.exp);}
	|	^('/' op1=expression[sc] op2=expression[sc]) {$exp = new Divide($op1.exp, $op2.exp);}
	|	^('%' op1=expression[sc] op2=expression[sc]) {$exp = new Mod($op1.exp, $op2.exp);}
	|	^('|' op1=expression[sc] op2=expression[sc]) {$exp = new Or($op1.exp, $op2.exp);}
	|	NUMBER {$exp = new shiro.expressions.Number(Float.parseFloat($NUMBER.text));}
	| 	path[sc] {$exp = $path.p;}
	;