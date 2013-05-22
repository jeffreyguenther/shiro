// $ANTLR 3.4 /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g 2012-11-16 16:42:35

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


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class GraphBuilderPass extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ACTIVATION", "ACTIVATION_LIST", "COMMENT", "DIGIT", "EVAL_SELECT", "IDENT", "LCLETTER", "NEGATION", "NEWLINE", "NODE_STMT", "NODE_UPDATE_PORT", "NUMBER", "PATH", "PORT_ASSIGNMENT", "PORT_DECL", "PORT_INDEX", "PORT_INIT", "PORT_TAG", "PRODUCES", "STATE_DECL", "STRING_LITERAL", "SUBJ_NODE_PROD", "SUBJ_SELECT", "UCLETTER", "WS", "'%'", "'('", "')'", "'*'", "'+'", "','", "'-'", "'->'", "'.'", "'/'", "'<'", "'>'", "'Comment'", "'Graph'", "'Parent'", "'Time'", "'['", "']'", "'begin'", "'collection'", "'end'", "'eval'", "'graph'", "'node'", "'port'", "'state'", "'subjunct'", "'subjunctive node'", "'view'", "'|'"
    };

    public static final int EOF=-1;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__50=50;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int ACTIVATION=4;
    public static final int ACTIVATION_LIST=5;
    public static final int COMMENT=6;
    public static final int DIGIT=7;
    public static final int EVAL_SELECT=8;
    public static final int IDENT=9;
    public static final int LCLETTER=10;
    public static final int NEGATION=11;
    public static final int NEWLINE=12;
    public static final int NODE_STMT=13;
    public static final int NODE_UPDATE_PORT=14;
    public static final int NUMBER=15;
    public static final int PATH=16;
    public static final int PORT_ASSIGNMENT=17;
    public static final int PORT_DECL=18;
    public static final int PORT_INDEX=19;
    public static final int PORT_INIT=20;
    public static final int PORT_TAG=21;
    public static final int PRODUCES=22;
    public static final int STATE_DECL=23;
    public static final int STRING_LITERAL=24;
    public static final int SUBJ_NODE_PROD=25;
    public static final int SUBJ_SELECT=26;
    public static final int UCLETTER=27;
    public static final int WS=28;

    // delegates
    public TreeParser[] getDelegates() {
        return new TreeParser[] {};
    }

    // delegators


    public GraphBuilderPass(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public GraphBuilderPass(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return GraphBuilderPass.tokenNames; }
    public String getGrammarFileName() { return "/Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g"; }


    SubjunctiveParametricSystem pSystem;
    List<DependencyRelation<Port>> deps;


    public static class shiro_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "shiro"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:29:1: shiro[SubjunctiveParametricSystem ps] : ( statement )+ ;
    public final GraphBuilderPass.shiro_return shiro(SubjunctiveParametricSystem ps) throws RecognitionException {
        GraphBuilderPass.shiro_return retval = new GraphBuilderPass.shiro_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        GraphBuilderPass.statement_return statement1 =null;




        pSystem = ps;
        // initalize the list of dependencies
        deps = new ArrayList<DependencyRelation<Port>>();


        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:36:3: ( ( statement )+ )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:36:7: ( statement )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:36:7: ( statement )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==NODE_STMT||LA1_0==STATE_DECL||LA1_0==48||LA1_0==51||(LA1_0 >= 56 && LA1_0 <= 57)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:36:7: statement
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_statement_in_shiro60);
            	    statement1=statement();

            	    state._fsp--;

            	    adaptor.addChild(root_0, statement1.getTree());


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "shiro"


    public static class statement_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "statement"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:39:1: statement : ( nodestmt | sNode | graphDecl | statestmt | collection | view );
    public final GraphBuilderPass.statement_return statement() throws RecognitionException {
        GraphBuilderPass.statement_return retval = new GraphBuilderPass.statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        GraphBuilderPass.nodestmt_return nodestmt2 =null;

        GraphBuilderPass.sNode_return sNode3 =null;

        GraphBuilderPass.graphDecl_return graphDecl4 =null;

        GraphBuilderPass.statestmt_return statestmt5 =null;

        GraphBuilderPass.collection_return collection6 =null;

        GraphBuilderPass.view_return view7 =null;



        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:40:3: ( nodestmt | sNode | graphDecl | statestmt | collection | view )
            int alt2=6;
            switch ( input.LA(1) ) {
            case NODE_STMT:
                {
                alt2=1;
                }
                break;
            case 56:
                {
                alt2=2;
                }
                break;
            case 51:
                {
                alt2=3;
                }
                break;
            case STATE_DECL:
                {
                alt2=4;
                }
                break;
            case 48:
                {
                alt2=5;
                }
                break;
            case 57:
                {
                alt2=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:40:5: nodestmt
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_nodestmt_in_statement77);
                    nodestmt2=nodestmt();

                    state._fsp--;

                    adaptor.addChild(root_0, nodestmt2.getTree());


                    }
                    break;
                case 2 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:41:5: sNode
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_sNode_in_statement83);
                    sNode3=sNode();

                    state._fsp--;

                    adaptor.addChild(root_0, sNode3.getTree());


                    }
                    break;
                case 3 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:42:5: graphDecl
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_graphDecl_in_statement89);
                    graphDecl4=graphDecl();

                    state._fsp--;

                    adaptor.addChild(root_0, graphDecl4.getTree());


                    }
                    break;
                case 4 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:43:5: statestmt
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statestmt_in_statement95);
                    statestmt5=statestmt();

                    state._fsp--;

                    adaptor.addChild(root_0, statestmt5.getTree());


                    }
                    break;
                case 5 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:44:5: collection
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_collection_in_statement101);
                    collection6=collection();

                    state._fsp--;

                    adaptor.addChild(root_0, collection6.getTree());


                    }
                    break;
                case 6 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:45:5: view
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_view_in_statement107);
                    view7=view();

                    state._fsp--;

                    adaptor.addChild(root_0, view7.getTree());


                    }
                    break;

            }
            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "statement"


    public static class view_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "view"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:48:1: view : ^( 'view' IDENT mfName IDENT ) ;
    public final GraphBuilderPass.view_return view() throws RecognitionException {
        GraphBuilderPass.view_return retval = new GraphBuilderPass.view_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree string_literal8=null;
        CommonTree IDENT9=null;
        CommonTree IDENT11=null;
        GraphBuilderPass.mfName_return mfName10 =null;


        CommonTree string_literal8_tree=null;
        CommonTree IDENT9_tree=null;
        CommonTree IDENT11_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:48:7: ( ^( 'view' IDENT mfName IDENT ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:48:9: ^( 'view' IDENT mfName IDENT )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            string_literal8=(CommonTree)match(input,57,FOLLOW_57_in_view122); 
            string_literal8_tree = (CommonTree)adaptor.dupNode(string_literal8);


            root_1 = (CommonTree)adaptor.becomeRoot(string_literal8_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            IDENT9=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_view124); 
            IDENT9_tree = (CommonTree)adaptor.dupNode(IDENT9);


            adaptor.addChild(root_1, IDENT9_tree);


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_mfName_in_view126);
            mfName10=mfName();

            state._fsp--;

            adaptor.addChild(root_1, mfName10.getTree());


            _last = (CommonTree)input.LT(1);
            IDENT11=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_view128); 
            IDENT11_tree = (CommonTree)adaptor.dupNode(IDENT11);


            adaptor.addChild(root_1, IDENT11_tree);


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "view"


    public static class collection_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "collection"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:51:1: collection : ^( 'collection' IDENT orderingFunc path[null] ( collItem )+ ) ;
    public final GraphBuilderPass.collection_return collection() throws RecognitionException {
        GraphBuilderPass.collection_return retval = new GraphBuilderPass.collection_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree string_literal12=null;
        CommonTree IDENT13=null;
        GraphBuilderPass.orderingFunc_return orderingFunc14 =null;

        GraphBuilderPass.path_return path15 =null;

        GraphBuilderPass.collItem_return collItem16 =null;


        CommonTree string_literal12_tree=null;
        CommonTree IDENT13_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:52:3: ( ^( 'collection' IDENT orderingFunc path[null] ( collItem )+ ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:52:5: ^( 'collection' IDENT orderingFunc path[null] ( collItem )+ )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            string_literal12=(CommonTree)match(input,48,FOLLOW_48_in_collection145); 
            string_literal12_tree = (CommonTree)adaptor.dupNode(string_literal12);


            root_1 = (CommonTree)adaptor.becomeRoot(string_literal12_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            IDENT13=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_collection147); 
            IDENT13_tree = (CommonTree)adaptor.dupNode(IDENT13);


            adaptor.addChild(root_1, IDENT13_tree);


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_orderingFunc_in_collection149);
            orderingFunc14=orderingFunc();

            state._fsp--;

            adaptor.addChild(root_1, orderingFunc14.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_path_in_collection151);
            path15=path(null);

            state._fsp--;

            adaptor.addChild(root_1, path15.getTree());


            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:52:50: ( collItem )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==IDENT) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:52:50: collItem
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_collItem_in_collection154);
            	    collItem16=collItem();

            	    state._fsp--;

            	    adaptor.addChild(root_1, collItem16.getTree());


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "collection"


    public static class collItem_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "collItem"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:55:1: collItem : IDENT ;
    public final GraphBuilderPass.collItem_return collItem() throws RecognitionException {
        GraphBuilderPass.collItem_return retval = new GraphBuilderPass.collItem_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree IDENT17=null;

        CommonTree IDENT17_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:55:9: ( IDENT )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:55:11: IDENT
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            IDENT17=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_collItem166); 
            IDENT17_tree = (CommonTree)adaptor.dupNode(IDENT17);


            adaptor.addChild(root_0, IDENT17_tree);


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "collItem"


    public static class orderingFunc_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "orderingFunc"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:58:1: orderingFunc : IDENT ;
    public final GraphBuilderPass.orderingFunc_return orderingFunc() throws RecognitionException {
        GraphBuilderPass.orderingFunc_return retval = new GraphBuilderPass.orderingFunc_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree IDENT18=null;

        CommonTree IDENT18_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:59:3: ( IDENT )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:59:5: IDENT
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            IDENT18=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_orderingFunc179); 
            IDENT18_tree = (CommonTree)adaptor.dupNode(IDENT18);


            adaptor.addChild(root_0, IDENT18_tree);


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "orderingFunc"


    public static class statestmt_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "statestmt"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:62:1: statestmt : ^( STATE_DECL stateHeader ) ;
    public final GraphBuilderPass.statestmt_return statestmt() throws RecognitionException {
        GraphBuilderPass.statestmt_return retval = new GraphBuilderPass.statestmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STATE_DECL19=null;
        GraphBuilderPass.stateHeader_return stateHeader20 =null;


        CommonTree STATE_DECL19_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:63:3: ( ^( STATE_DECL stateHeader ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:63:5: ^( STATE_DECL stateHeader )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            STATE_DECL19=(CommonTree)match(input,STATE_DECL,FOLLOW_STATE_DECL_in_statestmt195); 
            STATE_DECL19_tree = (CommonTree)adaptor.dupNode(STATE_DECL19);


            root_1 = (CommonTree)adaptor.becomeRoot(STATE_DECL19_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_stateHeader_in_statestmt197);
            stateHeader20=stateHeader();

            state._fsp--;

            adaptor.addChild(root_1, stateHeader20.getTree());


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "statestmt"


    public static class stateHeader_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateHeader"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:66:1: stateHeader : ( stateTimeStmt | stateCommentStmt | stateParentStmt | stateGraphStmt | activation )+ ;
    public final GraphBuilderPass.stateHeader_return stateHeader() throws RecognitionException {
        GraphBuilderPass.stateHeader_return retval = new GraphBuilderPass.stateHeader_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        GraphBuilderPass.stateTimeStmt_return stateTimeStmt21 =null;

        GraphBuilderPass.stateCommentStmt_return stateCommentStmt22 =null;

        GraphBuilderPass.stateParentStmt_return stateParentStmt23 =null;

        GraphBuilderPass.stateGraphStmt_return stateGraphStmt24 =null;

        GraphBuilderPass.activation_return activation25 =null;



        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:67:3: ( ( stateTimeStmt | stateCommentStmt | stateParentStmt | stateGraphStmt | activation )+ )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:67:7: ( stateTimeStmt | stateCommentStmt | stateParentStmt | stateGraphStmt | activation )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:67:7: ( stateTimeStmt | stateCommentStmt | stateParentStmt | stateGraphStmt | activation )+
            int cnt4=0;
            loop4:
            do {
                int alt4=6;
                switch ( input.LA(1) ) {
                case 44:
                    {
                    alt4=1;
                    }
                    break;
                case 41:
                    {
                    alt4=2;
                    }
                    break;
                case 43:
                    {
                    alt4=3;
                    }
                    break;
                case 42:
                    {
                    alt4=4;
                    }
                    break;
                case ACTIVATION:
                    {
                    alt4=5;
                    }
                    break;

                }

                switch (alt4) {
            	case 1 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:67:8: stateTimeStmt
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_stateTimeStmt_in_stateHeader216);
            	    stateTimeStmt21=stateTimeStmt();

            	    state._fsp--;

            	    adaptor.addChild(root_0, stateTimeStmt21.getTree());


            	    }
            	    break;
            	case 2 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:67:24: stateCommentStmt
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_stateCommentStmt_in_stateHeader220);
            	    stateCommentStmt22=stateCommentStmt();

            	    state._fsp--;

            	    adaptor.addChild(root_0, stateCommentStmt22.getTree());


            	    }
            	    break;
            	case 3 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:67:43: stateParentStmt
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_stateParentStmt_in_stateHeader224);
            	    stateParentStmt23=stateParentStmt();

            	    state._fsp--;

            	    adaptor.addChild(root_0, stateParentStmt23.getTree());


            	    }
            	    break;
            	case 4 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:67:60: stateGraphStmt
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_stateGraphStmt_in_stateHeader227);
            	    stateGraphStmt24=stateGraphStmt();

            	    state._fsp--;

            	    adaptor.addChild(root_0, stateGraphStmt24.getTree());


            	    }
            	    break;
            	case 5 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:67:76: activation
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_activation_in_stateHeader230);
            	    activation25=activation();

            	    state._fsp--;

            	    adaptor.addChild(root_0, activation25.getTree());


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateHeader"


    public static class stateTimeStmt_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateTimeStmt"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:70:1: stateTimeStmt : ^( 'Time' time ) ;
    public final GraphBuilderPass.stateTimeStmt_return stateTimeStmt() throws RecognitionException {
        GraphBuilderPass.stateTimeStmt_return retval = new GraphBuilderPass.stateTimeStmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree string_literal26=null;
        GraphBuilderPass.time_return time27 =null;


        CommonTree string_literal26_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:71:3: ( ^( 'Time' time ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:71:5: ^( 'Time' time )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            string_literal26=(CommonTree)match(input,44,FOLLOW_44_in_stateTimeStmt251); 
            string_literal26_tree = (CommonTree)adaptor.dupNode(string_literal26);


            root_1 = (CommonTree)adaptor.becomeRoot(string_literal26_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_time_in_stateTimeStmt253);
            time27=time();

            state._fsp--;

            adaptor.addChild(root_1, time27.getTree());


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateTimeStmt"


    public static class stateCommentStmt_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateCommentStmt"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:74:1: stateCommentStmt : ^( 'Comment' comment ) ;
    public final GraphBuilderPass.stateCommentStmt_return stateCommentStmt() throws RecognitionException {
        GraphBuilderPass.stateCommentStmt_return retval = new GraphBuilderPass.stateCommentStmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree string_literal28=null;
        GraphBuilderPass.comment_return comment29 =null;


        CommonTree string_literal28_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:75:3: ( ^( 'Comment' comment ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:75:5: ^( 'Comment' comment )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            string_literal28=(CommonTree)match(input,41,FOLLOW_41_in_stateCommentStmt268); 
            string_literal28_tree = (CommonTree)adaptor.dupNode(string_literal28);


            root_1 = (CommonTree)adaptor.becomeRoot(string_literal28_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_comment_in_stateCommentStmt270);
            comment29=comment();

            state._fsp--;

            adaptor.addChild(root_1, comment29.getTree());


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateCommentStmt"


    public static class stateParentStmt_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateParentStmt"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:78:1: stateParentStmt : ^( 'Parent' stateParent ) ;
    public final GraphBuilderPass.stateParentStmt_return stateParentStmt() throws RecognitionException {
        GraphBuilderPass.stateParentStmt_return retval = new GraphBuilderPass.stateParentStmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree string_literal30=null;
        GraphBuilderPass.stateParent_return stateParent31 =null;


        CommonTree string_literal30_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:79:3: ( ^( 'Parent' stateParent ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:79:5: ^( 'Parent' stateParent )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            string_literal30=(CommonTree)match(input,43,FOLLOW_43_in_stateParentStmt289); 
            string_literal30_tree = (CommonTree)adaptor.dupNode(string_literal30);


            root_1 = (CommonTree)adaptor.becomeRoot(string_literal30_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_stateParent_in_stateParentStmt291);
            stateParent31=stateParent();

            state._fsp--;

            adaptor.addChild(root_1, stateParent31.getTree());


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateParentStmt"


    public static class stateGraphStmt_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateGraphStmt"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:82:1: stateGraphStmt : ^( 'Graph' stateGraph ) ;
    public final GraphBuilderPass.stateGraphStmt_return stateGraphStmt() throws RecognitionException {
        GraphBuilderPass.stateGraphStmt_return retval = new GraphBuilderPass.stateGraphStmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree string_literal32=null;
        GraphBuilderPass.stateGraph_return stateGraph33 =null;


        CommonTree string_literal32_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:83:3: ( ^( 'Graph' stateGraph ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:83:5: ^( 'Graph' stateGraph )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            string_literal32=(CommonTree)match(input,42,FOLLOW_42_in_stateGraphStmt308); 
            string_literal32_tree = (CommonTree)adaptor.dupNode(string_literal32);


            root_1 = (CommonTree)adaptor.becomeRoot(string_literal32_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_stateGraph_in_stateGraphStmt310);
            stateGraph33=stateGraph();

            state._fsp--;

            adaptor.addChild(root_1, stateGraph33.getTree());


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateGraphStmt"


    public static class stateName_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateName"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:86:1: stateName : IDENT ;
    public final GraphBuilderPass.stateName_return stateName() throws RecognitionException {
        GraphBuilderPass.stateName_return retval = new GraphBuilderPass.stateName_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree IDENT34=null;

        CommonTree IDENT34_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:87:3: ( IDENT )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:87:5: IDENT
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            IDENT34=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_stateName326); 
            IDENT34_tree = (CommonTree)adaptor.dupNode(IDENT34);


            adaptor.addChild(root_0, IDENT34_tree);


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateName"


    public static class time_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "time"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:90:1: time : STRING_LITERAL ;
    public final GraphBuilderPass.time_return time() throws RecognitionException {
        GraphBuilderPass.time_return retval = new GraphBuilderPass.time_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STRING_LITERAL35=null;

        CommonTree STRING_LITERAL35_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:90:7: ( STRING_LITERAL )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:90:9: STRING_LITERAL
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            STRING_LITERAL35=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_time340); 
            STRING_LITERAL35_tree = (CommonTree)adaptor.dupNode(STRING_LITERAL35);


            adaptor.addChild(root_0, STRING_LITERAL35_tree);


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "time"


    public static class comment_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "comment"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:93:1: comment : STRING_LITERAL ;
    public final GraphBuilderPass.comment_return comment() throws RecognitionException {
        GraphBuilderPass.comment_return retval = new GraphBuilderPass.comment_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STRING_LITERAL36=null;

        CommonTree STRING_LITERAL36_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:93:9: ( STRING_LITERAL )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:93:11: STRING_LITERAL
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            STRING_LITERAL36=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_comment355); 
            STRING_LITERAL36_tree = (CommonTree)adaptor.dupNode(STRING_LITERAL36);


            adaptor.addChild(root_0, STRING_LITERAL36_tree);


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "comment"


    public static class stateParent_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateParent"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:96:1: stateParent : IDENT ;
    public final GraphBuilderPass.stateParent_return stateParent() throws RecognitionException {
        GraphBuilderPass.stateParent_return retval = new GraphBuilderPass.stateParent_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree IDENT37=null;

        CommonTree IDENT37_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:97:3: ( IDENT )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:97:5: IDENT
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            IDENT37=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_stateParent370); 
            IDENT37_tree = (CommonTree)adaptor.dupNode(IDENT37);


            adaptor.addChild(root_0, IDENT37_tree);


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateParent"


    public static class stateGraph_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateGraph"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:100:1: stateGraph : IDENT ;
    public final GraphBuilderPass.stateGraph_return stateGraph() throws RecognitionException {
        GraphBuilderPass.stateGraph_return retval = new GraphBuilderPass.stateGraph_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree IDENT38=null;

        CommonTree IDENT38_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:101:3: ( IDENT )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:101:5: IDENT
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            IDENT38=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_stateGraph385); 
            IDENT38_tree = (CommonTree)adaptor.dupNode(IDENT38);


            adaptor.addChild(root_0, IDENT38_tree);


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateGraph"


    public static class activationPath_return extends TreeRuleReturnScope {
        public String symbolName;
        public String activePort;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "activationPath"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:104:1: activationPath returns [String symbolName, String activePort] : l= activation ( '.' ^ (r= activation | activationList ) )* ;
    public final GraphBuilderPass.activationPath_return activationPath() throws RecognitionException {
        GraphBuilderPass.activationPath_return retval = new GraphBuilderPass.activationPath_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree char_literal39=null;
        GraphBuilderPass.activation_return l =null;

        GraphBuilderPass.activation_return r =null;

        GraphBuilderPass.activationList_return activationList40 =null;


        CommonTree char_literal39_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:105:3: (l= activation ( '.' ^ (r= activation | activationList ) )* )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:105:5: l= activation ( '.' ^ (r= activation | activationList ) )*
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_activation_in_activationPath404);
            l=activation();

            state._fsp--;

            adaptor.addChild(root_0, l.getTree());


            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:105:18: ( '.' ^ (r= activation | activationList ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==37) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:105:19: '.' ^ (r= activation | activationList )
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    char_literal39=(CommonTree)match(input,37,FOLLOW_37_in_activationPath407); 
            	    char_literal39_tree = (CommonTree)adaptor.dupNode(char_literal39);


            	    root_0 = (CommonTree)adaptor.becomeRoot(char_literal39_tree, root_0);


            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:105:24: (r= activation | activationList )
            	    int alt5=2;
            	    int LA5_0 = input.LA(1);

            	    if ( (LA5_0==ACTIVATION) ) {
            	        alt5=1;
            	    }
            	    else if ( (LA5_0==ACTIVATION_LIST) ) {
            	        alt5=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 5, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt5) {
            	        case 1 :
            	            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:105:25: r= activation
            	            {
            	            _last = (CommonTree)input.LT(1);
            	            pushFollow(FOLLOW_activation_in_activationPath413);
            	            r=activation();

            	            state._fsp--;

            	            adaptor.addChild(root_0, r.getTree());


            	            }
            	            break;
            	        case 2 :
            	            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:105:40: activationList
            	            {
            	            _last = (CommonTree)input.LT(1);
            	            pushFollow(FOLLOW_activationList_in_activationPath417);
            	            activationList40=activationList();

            	            state._fsp--;

            	            adaptor.addChild(root_0, activationList40.getTree());


            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);



            retval.symbolName = l.symbolName;
            retval.activePort = l.activePort;


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "activationPath"


    public static class activationList_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "activationList"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:112:1: activationList : ^( ACTIVATION_LIST ( activation )+ ) ;
    public final GraphBuilderPass.activationList_return activationList() throws RecognitionException {
        GraphBuilderPass.activationList_return retval = new GraphBuilderPass.activationList_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree ACTIVATION_LIST41=null;
        GraphBuilderPass.activation_return activation42 =null;


        CommonTree ACTIVATION_LIST41_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:113:3: ( ^( ACTIVATION_LIST ( activation )+ ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:113:5: ^( ACTIVATION_LIST ( activation )+ )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            ACTIVATION_LIST41=(CommonTree)match(input,ACTIVATION_LIST,FOLLOW_ACTIVATION_LIST_in_activationList439); 
            ACTIVATION_LIST41_tree = (CommonTree)adaptor.dupNode(ACTIVATION_LIST41);


            root_1 = (CommonTree)adaptor.becomeRoot(ACTIVATION_LIST41_tree, root_1);


            match(input, Token.DOWN, null); 
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:113:23: ( activation )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==ACTIVATION) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:113:23: activation
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_activation_in_activationList441);
            	    activation42=activation();

            	    state._fsp--;

            	    adaptor.addChild(root_1, activation42.getTree());


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "activationList"


    public static class activation_return extends TreeRuleReturnScope {
        public String symbolName;
        public String activePort;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "activation"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:116:1: activation returns [String symbolName, String activePort] : ^( ACTIVATION i1= IDENT (i2= IDENT )? ) ;
    public final GraphBuilderPass.activation_return activation() throws RecognitionException {
        GraphBuilderPass.activation_return retval = new GraphBuilderPass.activation_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree i1=null;
        CommonTree i2=null;
        CommonTree ACTIVATION43=null;

        CommonTree i1_tree=null;
        CommonTree i2_tree=null;
        CommonTree ACTIVATION43_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:117:3: ( ^( ACTIVATION i1= IDENT (i2= IDENT )? ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:117:5: ^( ACTIVATION i1= IDENT (i2= IDENT )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            ACTIVATION43=(CommonTree)match(input,ACTIVATION,FOLLOW_ACTIVATION_in_activation463); 
            ACTIVATION43_tree = (CommonTree)adaptor.dupNode(ACTIVATION43);


            root_1 = (CommonTree)adaptor.becomeRoot(ACTIVATION43_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            i1=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_activation467); 
            i1_tree = (CommonTree)adaptor.dupNode(i1);


            adaptor.addChild(root_1, i1_tree);


            retval.symbolName = (i1!=null?i1.getText():null);

            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:119:13: (i2= IDENT )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==IDENT) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:119:14: i2= IDENT
                    {
                    _last = (CommonTree)input.LT(1);
                    i2=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_activation497); 
                    i2_tree = (CommonTree)adaptor.dupNode(i2);


                    adaptor.addChild(root_1, i2_tree);


                    retval.activePort = (i2!=null?i2.getText():null);

                    }
                    break;

            }


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "activation"


    public static class graphDecl_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "graphDecl"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:122:1: graphDecl : ^( 'graph' IDENT ( graphLine )+ ) ;
    public final GraphBuilderPass.graphDecl_return graphDecl() throws RecognitionException {
        GraphBuilderPass.graphDecl_return retval = new GraphBuilderPass.graphDecl_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree string_literal44=null;
        CommonTree IDENT45=null;
        GraphBuilderPass.graphLine_return graphLine46 =null;


        CommonTree string_literal44_tree=null;
        CommonTree IDENT45_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:126:3: ( ^( 'graph' IDENT ( graphLine )+ ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:126:5: ^( 'graph' IDENT ( graphLine )+ )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            string_literal44=(CommonTree)match(input,51,FOLLOW_51_in_graphDecl522); 
            string_literal44_tree = (CommonTree)adaptor.dupNode(string_literal44);


            root_1 = (CommonTree)adaptor.becomeRoot(string_literal44_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            IDENT45=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_graphDecl524); 
            IDENT45_tree = (CommonTree)adaptor.dupNode(IDENT45);


            adaptor.addChild(root_1, IDENT45_tree);


            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:126:21: ( graphLine )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==PORT_ASSIGNMENT||LA9_0==36) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:126:21: graphLine
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_graphLine_in_graphDecl526);
            	    graphLine46=graphLine();

            	    state._fsp--;

            	    adaptor.addChild(root_1, graphLine46.getTree());


            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }




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

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "graphDecl"


    public static class graphLine_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "graphLine"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:149:1: graphLine : ( nodeProduction | portAssignment );
    public final GraphBuilderPass.graphLine_return graphLine() throws RecognitionException {
        GraphBuilderPass.graphLine_return retval = new GraphBuilderPass.graphLine_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        GraphBuilderPass.nodeProduction_return nodeProduction47 =null;

        GraphBuilderPass.portAssignment_return portAssignment48 =null;



        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:150:3: ( nodeProduction | portAssignment )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==36) ) {
                alt10=1;
            }
            else if ( (LA10_0==PORT_ASSIGNMENT) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }
            switch (alt10) {
                case 1 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:150:5: nodeProduction
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_nodeProduction_in_graphLine545);
                    nodeProduction47=nodeProduction();

                    state._fsp--;

                    adaptor.addChild(root_0, nodeProduction47.getTree());


                    }
                    break;
                case 2 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:151:5: portAssignment
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_portAssignment_in_graphLine552);
                    portAssignment48=portAssignment();

                    state._fsp--;

                    adaptor.addChild(root_0, portAssignment48.getTree());


                    }
                    break;

            }
            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "graphLine"


    public static class nodeInternal_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nodeInternal"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:154:1: nodeInternal : ( nodeProduction | portAssignment | portstmt | nodestmt | sNode | NEWLINE !)+ ;
    public final GraphBuilderPass.nodeInternal_return nodeInternal() throws RecognitionException {
        GraphBuilderPass.nodeInternal_return retval = new GraphBuilderPass.nodeInternal_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree NEWLINE54=null;
        GraphBuilderPass.nodeProduction_return nodeProduction49 =null;

        GraphBuilderPass.portAssignment_return portAssignment50 =null;

        GraphBuilderPass.portstmt_return portstmt51 =null;

        GraphBuilderPass.nodestmt_return nodestmt52 =null;

        GraphBuilderPass.sNode_return sNode53 =null;


        CommonTree NEWLINE54_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:155:3: ( ( nodeProduction | portAssignment | portstmt | nodestmt | sNode | NEWLINE !)+ )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:155:5: ( nodeProduction | portAssignment | portstmt | nodestmt | sNode | NEWLINE !)+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:155:5: ( nodeProduction | portAssignment | portstmt | nodestmt | sNode | NEWLINE !)+
            int cnt11=0;
            loop11:
            do {
                int alt11=7;
                switch ( input.LA(1) ) {
                case 36:
                    {
                    alt11=1;
                    }
                    break;
                case PORT_ASSIGNMENT:
                    {
                    alt11=2;
                    }
                    break;
                case PORT_DECL:
                case PORT_INIT:
                    {
                    alt11=3;
                    }
                    break;
                case NODE_STMT:
                    {
                    alt11=4;
                    }
                    break;
                case 56:
                    {
                    alt11=5;
                    }
                    break;
                case NEWLINE:
                    {
                    alt11=6;
                    }
                    break;

                }

                switch (alt11) {
            	case 1 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:155:6: nodeProduction
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_nodeProduction_in_nodeInternal568);
            	    nodeProduction49=nodeProduction();

            	    state._fsp--;

            	    adaptor.addChild(root_0, nodeProduction49.getTree());


            	    }
            	    break;
            	case 2 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:155:23: portAssignment
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_portAssignment_in_nodeInternal572);
            	    portAssignment50=portAssignment();

            	    state._fsp--;

            	    adaptor.addChild(root_0, portAssignment50.getTree());


            	    }
            	    break;
            	case 3 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:155:40: portstmt
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_portstmt_in_nodeInternal576);
            	    portstmt51=portstmt();

            	    state._fsp--;

            	    adaptor.addChild(root_0, portstmt51.getTree());


            	    }
            	    break;
            	case 4 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:155:51: nodestmt
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_nodestmt_in_nodeInternal580);
            	    nodestmt52=nodestmt();

            	    state._fsp--;

            	    adaptor.addChild(root_0, nodestmt52.getTree());


            	    }
            	    break;
            	case 5 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:155:62: sNode
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_sNode_in_nodeInternal584);
            	    sNode53=sNode();

            	    state._fsp--;

            	    adaptor.addChild(root_0, sNode53.getTree());


            	    }
            	    break;
            	case 6 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:155:70: NEWLINE !
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    NEWLINE54=(CommonTree)match(input,NEWLINE,FOLLOW_NEWLINE_in_nodeInternal588); 

            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nodeInternal"


    public static class nodestmt_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nodestmt"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:158:1: nodestmt : ^( NODE_STMT IDENT ( activeSelector )? nodeInternal ) ;
    public final GraphBuilderPass.nodestmt_return nodestmt() throws RecognitionException {
        GraphBuilderPass.nodestmt_return retval = new GraphBuilderPass.nodestmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree NODE_STMT55=null;
        CommonTree IDENT56=null;
        GraphBuilderPass.activeSelector_return activeSelector57 =null;

        GraphBuilderPass.nodeInternal_return nodeInternal58 =null;


        CommonTree NODE_STMT55_tree=null;
        CommonTree IDENT56_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:159:3: ( ^( NODE_STMT IDENT ( activeSelector )? nodeInternal ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:159:5: ^( NODE_STMT IDENT ( activeSelector )? nodeInternal )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            NODE_STMT55=(CommonTree)match(input,NODE_STMT,FOLLOW_NODE_STMT_in_nodestmt608); 
            NODE_STMT55_tree = (CommonTree)adaptor.dupNode(NODE_STMT55);


            root_1 = (CommonTree)adaptor.becomeRoot(NODE_STMT55_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            IDENT56=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_nodestmt610); 
            IDENT56_tree = (CommonTree)adaptor.dupNode(IDENT56);


            adaptor.addChild(root_1, IDENT56_tree);


            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:159:23: ( activeSelector )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==EVAL_SELECT) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:159:23: activeSelector
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_activeSelector_in_nodestmt612);
                    activeSelector57=activeSelector();

                    state._fsp--;

                    adaptor.addChild(root_1, activeSelector57.getTree());


                    }
                    break;

            }


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_nodeInternal_in_nodestmt615);
            nodeInternal58=nodeInternal();

            state._fsp--;

            adaptor.addChild(root_1, nodeInternal58.getTree());


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nodestmt"


    public static class sNode_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "sNode"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:162:1: sNode : 'subjunctive node' ^ IDENT '[' ! subjunctSelector ']' ! 'begin' ! NEWLINE ! ( subjunctDeclNodeProd | subjunctDecl | NEWLINE !)+ 'end' !;
    public final GraphBuilderPass.sNode_return sNode() throws RecognitionException {
        GraphBuilderPass.sNode_return retval = new GraphBuilderPass.sNode_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree string_literal59=null;
        CommonTree IDENT60=null;
        CommonTree char_literal61=null;
        CommonTree char_literal63=null;
        CommonTree string_literal64=null;
        CommonTree NEWLINE65=null;
        CommonTree NEWLINE68=null;
        CommonTree string_literal69=null;
        GraphBuilderPass.subjunctSelector_return subjunctSelector62 =null;

        GraphBuilderPass.subjunctDeclNodeProd_return subjunctDeclNodeProd66 =null;

        GraphBuilderPass.subjunctDecl_return subjunctDecl67 =null;


        CommonTree string_literal59_tree=null;
        CommonTree IDENT60_tree=null;
        CommonTree char_literal61_tree=null;
        CommonTree char_literal63_tree=null;
        CommonTree string_literal64_tree=null;
        CommonTree NEWLINE65_tree=null;
        CommonTree NEWLINE68_tree=null;
        CommonTree string_literal69_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:163:3: ( 'subjunctive node' ^ IDENT '[' ! subjunctSelector ']' ! 'begin' ! NEWLINE ! ( subjunctDeclNodeProd | subjunctDecl | NEWLINE !)+ 'end' !)
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:163:5: 'subjunctive node' ^ IDENT '[' ! subjunctSelector ']' ! 'begin' ! NEWLINE ! ( subjunctDeclNodeProd | subjunctDecl | NEWLINE !)+ 'end' !
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            string_literal59=(CommonTree)match(input,56,FOLLOW_56_in_sNode631); 
            string_literal59_tree = (CommonTree)adaptor.dupNode(string_literal59);


            root_0 = (CommonTree)adaptor.becomeRoot(string_literal59_tree, root_0);


            _last = (CommonTree)input.LT(1);
            IDENT60=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_sNode634); 
            IDENT60_tree = (CommonTree)adaptor.dupNode(IDENT60);


            adaptor.addChild(root_0, IDENT60_tree);


            _last = (CommonTree)input.LT(1);
            char_literal61=(CommonTree)match(input,45,FOLLOW_45_in_sNode636); 

            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_subjunctSelector_in_sNode639);
            subjunctSelector62=subjunctSelector();

            state._fsp--;

            adaptor.addChild(root_0, subjunctSelector62.getTree());


            _last = (CommonTree)input.LT(1);
            char_literal63=(CommonTree)match(input,46,FOLLOW_46_in_sNode641); 

            _last = (CommonTree)input.LT(1);
            string_literal64=(CommonTree)match(input,47,FOLLOW_47_in_sNode644); 

            _last = (CommonTree)input.LT(1);
            NEWLINE65=(CommonTree)match(input,NEWLINE,FOLLOW_NEWLINE_in_sNode647); 

            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:164:5: ( subjunctDeclNodeProd | subjunctDecl | NEWLINE !)+
            int cnt13=0;
            loop13:
            do {
                int alt13=4;
                switch ( input.LA(1) ) {
                case SUBJ_NODE_PROD:
                    {
                    alt13=1;
                    }
                    break;
                case 55:
                    {
                    alt13=2;
                    }
                    break;
                case NEWLINE:
                    {
                    alt13=3;
                    }
                    break;

                }

                switch (alt13) {
            	case 1 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:164:6: subjunctDeclNodeProd
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_subjunctDeclNodeProd_in_sNode655);
            	    subjunctDeclNodeProd66=subjunctDeclNodeProd();

            	    state._fsp--;

            	    adaptor.addChild(root_0, subjunctDeclNodeProd66.getTree());


            	    }
            	    break;
            	case 2 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:164:29: subjunctDecl
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_subjunctDecl_in_sNode659);
            	    subjunctDecl67=subjunctDecl();

            	    state._fsp--;

            	    adaptor.addChild(root_0, subjunctDecl67.getTree());


            	    }
            	    break;
            	case 3 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:164:44: NEWLINE !
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    NEWLINE68=(CommonTree)match(input,NEWLINE,FOLLOW_NEWLINE_in_sNode663); 

            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


            _last = (CommonTree)input.LT(1);
            string_literal69=(CommonTree)match(input,49,FOLLOW_49_in_sNode672); 

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "sNode"


    public static class subjunctDeclNodeProd_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "subjunctDeclNodeProd"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:168:1: subjunctDeclNodeProd : ^( SUBJ_NODE_PROD IDENT IDENT nodeInternal ) ;
    public final GraphBuilderPass.subjunctDeclNodeProd_return subjunctDeclNodeProd() throws RecognitionException {
        GraphBuilderPass.subjunctDeclNodeProd_return retval = new GraphBuilderPass.subjunctDeclNodeProd_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree SUBJ_NODE_PROD70=null;
        CommonTree IDENT71=null;
        CommonTree IDENT72=null;
        GraphBuilderPass.nodeInternal_return nodeInternal73 =null;


        CommonTree SUBJ_NODE_PROD70_tree=null;
        CommonTree IDENT71_tree=null;
        CommonTree IDENT72_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:169:3: ( ^( SUBJ_NODE_PROD IDENT IDENT nodeInternal ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:169:5: ^( SUBJ_NODE_PROD IDENT IDENT nodeInternal )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            SUBJ_NODE_PROD70=(CommonTree)match(input,SUBJ_NODE_PROD,FOLLOW_SUBJ_NODE_PROD_in_subjunctDeclNodeProd689); 
            SUBJ_NODE_PROD70_tree = (CommonTree)adaptor.dupNode(SUBJ_NODE_PROD70);


            root_1 = (CommonTree)adaptor.becomeRoot(SUBJ_NODE_PROD70_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            IDENT71=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_subjunctDeclNodeProd691); 
            IDENT71_tree = (CommonTree)adaptor.dupNode(IDENT71);


            adaptor.addChild(root_1, IDENT71_tree);


            _last = (CommonTree)input.LT(1);
            IDENT72=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_subjunctDeclNodeProd693); 
            IDENT72_tree = (CommonTree)adaptor.dupNode(IDENT72);


            adaptor.addChild(root_1, IDENT72_tree);


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_nodeInternal_in_subjunctDeclNodeProd695);
            nodeInternal73=nodeInternal();

            state._fsp--;

            adaptor.addChild(root_1, nodeInternal73.getTree());


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "subjunctDeclNodeProd"


    public static class subjunctDecl_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "subjunctDecl"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:172:1: subjunctDecl : 'subjunct' ^ IDENT ( '[' ! activeSelector ']' !)? 'begin' ! NEWLINE ! nodeInternal 'end' !;
    public final GraphBuilderPass.subjunctDecl_return subjunctDecl() throws RecognitionException {
        GraphBuilderPass.subjunctDecl_return retval = new GraphBuilderPass.subjunctDecl_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree string_literal74=null;
        CommonTree IDENT75=null;
        CommonTree char_literal76=null;
        CommonTree char_literal78=null;
        CommonTree string_literal79=null;
        CommonTree NEWLINE80=null;
        CommonTree string_literal82=null;
        GraphBuilderPass.activeSelector_return activeSelector77 =null;

        GraphBuilderPass.nodeInternal_return nodeInternal81 =null;


        CommonTree string_literal74_tree=null;
        CommonTree IDENT75_tree=null;
        CommonTree char_literal76_tree=null;
        CommonTree char_literal78_tree=null;
        CommonTree string_literal79_tree=null;
        CommonTree NEWLINE80_tree=null;
        CommonTree string_literal82_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:173:3: ( 'subjunct' ^ IDENT ( '[' ! activeSelector ']' !)? 'begin' ! NEWLINE ! nodeInternal 'end' !)
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:173:5: 'subjunct' ^ IDENT ( '[' ! activeSelector ']' !)? 'begin' ! NEWLINE ! nodeInternal 'end' !
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            string_literal74=(CommonTree)match(input,55,FOLLOW_55_in_subjunctDecl712); 
            string_literal74_tree = (CommonTree)adaptor.dupNode(string_literal74);


            root_0 = (CommonTree)adaptor.becomeRoot(string_literal74_tree, root_0);


            _last = (CommonTree)input.LT(1);
            IDENT75=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_subjunctDecl715); 
            IDENT75_tree = (CommonTree)adaptor.dupNode(IDENT75);


            adaptor.addChild(root_0, IDENT75_tree);


            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:173:23: ( '[' ! activeSelector ']' !)?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==45) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:173:24: '[' ! activeSelector ']' !
                    {
                    _last = (CommonTree)input.LT(1);
                    char_literal76=(CommonTree)match(input,45,FOLLOW_45_in_subjunctDecl718); 

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_activeSelector_in_subjunctDecl721);
                    activeSelector77=activeSelector();

                    state._fsp--;

                    adaptor.addChild(root_0, activeSelector77.getTree());


                    _last = (CommonTree)input.LT(1);
                    char_literal78=(CommonTree)match(input,46,FOLLOW_46_in_subjunctDecl723); 

                    }
                    break;

            }


            _last = (CommonTree)input.LT(1);
            string_literal79=(CommonTree)match(input,47,FOLLOW_47_in_subjunctDecl728); 

            _last = (CommonTree)input.LT(1);
            NEWLINE80=(CommonTree)match(input,NEWLINE,FOLLOW_NEWLINE_in_subjunctDecl731); 

            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_nodeInternal_in_subjunctDecl738);
            nodeInternal81=nodeInternal();

            state._fsp--;

            adaptor.addChild(root_0, nodeInternal81.getTree());


            _last = (CommonTree)input.LT(1);
            string_literal82=(CommonTree)match(input,49,FOLLOW_49_in_subjunctDecl744); 

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "subjunctDecl"


    public static class subjunctSelector_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "subjunctSelector"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:178:1: subjunctSelector : ^( SUBJ_SELECT IDENT ) ;
    public final GraphBuilderPass.subjunctSelector_return subjunctSelector() throws RecognitionException {
        GraphBuilderPass.subjunctSelector_return retval = new GraphBuilderPass.subjunctSelector_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree SUBJ_SELECT83=null;
        CommonTree IDENT84=null;

        CommonTree SUBJ_SELECT83_tree=null;
        CommonTree IDENT84_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:179:3: ( ^( SUBJ_SELECT IDENT ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:179:5: ^( SUBJ_SELECT IDENT )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            SUBJ_SELECT83=(CommonTree)match(input,SUBJ_SELECT,FOLLOW_SUBJ_SELECT_in_subjunctSelector759); 
            SUBJ_SELECT83_tree = (CommonTree)adaptor.dupNode(SUBJ_SELECT83);


            root_1 = (CommonTree)adaptor.becomeRoot(SUBJ_SELECT83_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            IDENT84=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_subjunctSelector761); 
            IDENT84_tree = (CommonTree)adaptor.dupNode(IDENT84);


            adaptor.addChild(root_1, IDENT84_tree);


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "subjunctSelector"


    public static class activeSelector_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "activeSelector"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:182:1: activeSelector : ^( EVAL_SELECT IDENT ) ;
    public final GraphBuilderPass.activeSelector_return activeSelector() throws RecognitionException {
        GraphBuilderPass.activeSelector_return retval = new GraphBuilderPass.activeSelector_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree EVAL_SELECT85=null;
        CommonTree IDENT86=null;

        CommonTree EVAL_SELECT85_tree=null;
        CommonTree IDENT86_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:183:3: ( ^( EVAL_SELECT IDENT ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:183:5: ^( EVAL_SELECT IDENT )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            EVAL_SELECT85=(CommonTree)match(input,EVAL_SELECT,FOLLOW_EVAL_SELECT_in_activeSelector780); 
            EVAL_SELECT85_tree = (CommonTree)adaptor.dupNode(EVAL_SELECT85);


            root_1 = (CommonTree)adaptor.becomeRoot(EVAL_SELECT85_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            IDENT86=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_activeSelector782); 
            IDENT86_tree = (CommonTree)adaptor.dupNode(IDENT86);


            adaptor.addChild(root_1, IDENT86_tree);


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "activeSelector"


    public static class nodeProduction_return extends TreeRuleReturnScope {
        public Path pathToReturn;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nodeProduction"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:186:1: nodeProduction returns [Path pathToReturn] : ( ^( '->' path[null] activationPath ) | ^( '->' l= nodeProduction activationPath ) );
    public final GraphBuilderPass.nodeProduction_return nodeProduction() throws RecognitionException {
        GraphBuilderPass.nodeProduction_return retval = new GraphBuilderPass.nodeProduction_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree string_literal87=null;
        CommonTree string_literal90=null;
        GraphBuilderPass.nodeProduction_return l =null;

        GraphBuilderPass.path_return path88 =null;

        GraphBuilderPass.activationPath_return activationPath89 =null;

        GraphBuilderPass.activationPath_return activationPath91 =null;


        CommonTree string_literal87_tree=null;
        CommonTree string_literal90_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:187:3: ( ^( '->' path[null] activationPath ) | ^( '->' l= nodeProduction activationPath ) )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==36) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==DOWN) ) {
                    int LA15_2 = input.LA(3);

                    if ( (LA15_2==PATH) ) {
                        alt15=1;
                    }
                    else if ( (LA15_2==36) ) {
                        alt15=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 15, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }
            switch (alt15) {
                case 1 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:187:5: ^( '->' path[null] activationPath )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    string_literal87=(CommonTree)match(input,36,FOLLOW_36_in_nodeProduction801); 
                    string_literal87_tree = (CommonTree)adaptor.dupNode(string_literal87);


                    root_1 = (CommonTree)adaptor.becomeRoot(string_literal87_tree, root_1);


                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_path_in_nodeProduction803);
                    path88=path(null);

                    state._fsp--;

                    adaptor.addChild(root_1, path88.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_activationPath_in_nodeProduction807);
                    activationPath89=activationPath();

                    state._fsp--;

                    adaptor.addChild(root_1, activationPath89.getTree());


                    match(input, Token.UP, null); 
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }



                    //create a new node from the AST with the given name
                    Node producedNode = pSystem.produceNodeFromPath((path88!=null?path88.p:null).getPathAsString(), (activationPath89!=null?activationPath89.symbolName:null));

                    	if(producedNode != null){
                    	//TODO: if the produced node is not added to the subjuncitve system object, add it.
                    	//activate the port for the produced node using activationPath.activatedPort
                    	
                    	  
                    	}else{
                    	
                    	}
                    	retval.pathToReturn = (path88!=null?path88.p:null);
                    System.out.println("Node declared: " 
                    	+ producedNode.getFullName() + " with active update " 
                    	+ producedNode.getSelectedEvaluatedPort());
                    System.out.println();


                    }
                    break;
                case 2 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:206:6: ^( '->' l= nodeProduction activationPath )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    string_literal90=(CommonTree)match(input,36,FOLLOW_36_in_nodeProduction818); 
                    string_literal90_tree = (CommonTree)adaptor.dupNode(string_literal90);


                    root_1 = (CommonTree)adaptor.becomeRoot(string_literal90_tree, root_1);


                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_nodeProduction_in_nodeProduction822);
                    l=nodeProduction();

                    state._fsp--;

                    adaptor.addChild(root_1, l.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_activationPath_in_nodeProduction824);
                    activationPath91=activationPath();

                    state._fsp--;

                    adaptor.addChild(root_1, activationPath91.getTree());


                    match(input, Token.UP, null); 
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }



                    Node producedNode = pSystem.produceNodeFromPath((l!=null?l.pathToReturn:null).getPathAsString(), (activationPath91!=null?activationPath91.symbolName:null));

                      if(producedNode != null){
                      //TODO: if the produced node is not added to the subjuncitve system object, add it.
                      //activate the port for the produced node using activationPath.activatedPort
                      
                        
                      }else{
                      
                      }
                      retval.pathToReturn = (l!=null?l.pathToReturn:null);
                    System.out.println("Node declared: " 
                    	+ producedNode.getFullName() + " with active update " 
                    	+ producedNode.getSelectedEvaluatedPort());
                    System.out.println();


                    }
                    break;

            }
            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nodeProduction"


    public static class portAssignment_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "portAssignment"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:226:1: portAssignment : ^( PORT_ASSIGNMENT path[null] mfparams[n] ) ;
    public final GraphBuilderPass.portAssignment_return portAssignment() throws RecognitionException {
        GraphBuilderPass.portAssignment_return retval = new GraphBuilderPass.portAssignment_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree PORT_ASSIGNMENT92=null;
        GraphBuilderPass.path_return path93 =null;

        GraphBuilderPass.mfparams_return mfparams94 =null;


        CommonTree PORT_ASSIGNMENT92_tree=null;


        Node n;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:230:3: ( ^( PORT_ASSIGNMENT path[null] mfparams[n] ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:230:5: ^( PORT_ASSIGNMENT path[null] mfparams[n] )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            PORT_ASSIGNMENT92=(CommonTree)match(input,PORT_ASSIGNMENT,FOLLOW_PORT_ASSIGNMENT_in_portAssignment847); 
            PORT_ASSIGNMENT92_tree = (CommonTree)adaptor.dupNode(PORT_ASSIGNMENT92);


            root_1 = (CommonTree)adaptor.becomeRoot(PORT_ASSIGNMENT92_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_path_in_portAssignment849);
            path93=path(null);

            state._fsp--;

            adaptor.addChild(root_1, path93.getTree());



            // look up the node object, so that it can be used as the scope object for the mf's expression's parameters.
            n = pSystem.getNode((path93!=null?path93.p:null));


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_mfparams_in_portAssignment856);
            mfparams94=mfparams(n);

            state._fsp--;

            adaptor.addChild(root_1, mfparams94.getTree());


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }



            // look up port based on the path
            try{
              Port p = (Port) pSystem.resolvePath((path93!=null?path93.p:null));
              // set the port's expression
              p.setArguments((mfparams94!=null?mfparams94.expressions:null));
              System.out.println(p + " args were set."); 
              System.out.println("Node is now:\n" + n); 
            }catch (PathNotFoundException pnfe){
              System.out.println(pnfe);
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "portAssignment"


    public static class portDecl_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "portDecl"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:251:1: portDecl : ^( PORT_DECL ^( PORT_TAG portType ) portName mfName ) ;
    public final GraphBuilderPass.portDecl_return portDecl() throws RecognitionException {
        GraphBuilderPass.portDecl_return retval = new GraphBuilderPass.portDecl_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree PORT_DECL95=null;
        CommonTree PORT_TAG96=null;
        GraphBuilderPass.portType_return portType97 =null;

        GraphBuilderPass.portName_return portName98 =null;

        GraphBuilderPass.mfName_return mfName99 =null;


        CommonTree PORT_DECL95_tree=null;
        CommonTree PORT_TAG96_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:252:3: ( ^( PORT_DECL ^( PORT_TAG portType ) portName mfName ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:252:5: ^( PORT_DECL ^( PORT_TAG portType ) portName mfName )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            PORT_DECL95=(CommonTree)match(input,PORT_DECL,FOLLOW_PORT_DECL_in_portDecl875); 
            PORT_DECL95_tree = (CommonTree)adaptor.dupNode(PORT_DECL95);


            root_1 = (CommonTree)adaptor.becomeRoot(PORT_DECL95_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_2 = _last;
            CommonTree _first_2 = null;
            CommonTree root_2 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            PORT_TAG96=(CommonTree)match(input,PORT_TAG,FOLLOW_PORT_TAG_in_portDecl878); 
            PORT_TAG96_tree = (CommonTree)adaptor.dupNode(PORT_TAG96);


            root_2 = (CommonTree)adaptor.becomeRoot(PORT_TAG96_tree, root_2);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_portType_in_portDecl880);
            portType97=portType();

            state._fsp--;

            adaptor.addChild(root_2, portType97.getTree());


            match(input, Token.UP, null); 
            adaptor.addChild(root_1, root_2);
            _last = _save_last_2;
            }


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_portName_in_portDecl883);
            portName98=portName();

            state._fsp--;

            adaptor.addChild(root_1, portName98.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_mfName_in_portDecl885);
            mfName99=mfName();

            state._fsp--;

            adaptor.addChild(root_1, mfName99.getTree());


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "portDecl"


    public static class portDeclInit_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "portDeclInit"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:255:1: portDeclInit : ^( PORT_INIT ^( PORT_TAG portType ) portName mfCall[null] ) ;
    public final GraphBuilderPass.portDeclInit_return portDeclInit() throws RecognitionException {
        GraphBuilderPass.portDeclInit_return retval = new GraphBuilderPass.portDeclInit_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree PORT_INIT100=null;
        CommonTree PORT_TAG101=null;
        GraphBuilderPass.portType_return portType102 =null;

        GraphBuilderPass.portName_return portName103 =null;

        GraphBuilderPass.mfCall_return mfCall104 =null;


        CommonTree PORT_INIT100_tree=null;
        CommonTree PORT_TAG101_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:256:3: ( ^( PORT_INIT ^( PORT_TAG portType ) portName mfCall[null] ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:256:5: ^( PORT_INIT ^( PORT_TAG portType ) portName mfCall[null] )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            PORT_INIT100=(CommonTree)match(input,PORT_INIT,FOLLOW_PORT_INIT_in_portDeclInit902); 
            PORT_INIT100_tree = (CommonTree)adaptor.dupNode(PORT_INIT100);


            root_1 = (CommonTree)adaptor.becomeRoot(PORT_INIT100_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_2 = _last;
            CommonTree _first_2 = null;
            CommonTree root_2 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            PORT_TAG101=(CommonTree)match(input,PORT_TAG,FOLLOW_PORT_TAG_in_portDeclInit905); 
            PORT_TAG101_tree = (CommonTree)adaptor.dupNode(PORT_TAG101);


            root_2 = (CommonTree)adaptor.becomeRoot(PORT_TAG101_tree, root_2);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_portType_in_portDeclInit907);
            portType102=portType();

            state._fsp--;

            adaptor.addChild(root_2, portType102.getTree());


            match(input, Token.UP, null); 
            adaptor.addChild(root_1, root_2);
            _last = _save_last_2;
            }


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_portName_in_portDeclInit910);
            portName103=portName();

            state._fsp--;

            adaptor.addChild(root_1, portName103.getTree());


            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_mfCall_in_portDeclInit912);
            mfCall104=mfCall(null);

            state._fsp--;

            adaptor.addChild(root_1, mfCall104.getTree());


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "portDeclInit"


    public static class portstmt_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "portstmt"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:259:1: portstmt : ( portDecl | portDeclInit );
    public final GraphBuilderPass.portstmt_return portstmt() throws RecognitionException {
        GraphBuilderPass.portstmt_return retval = new GraphBuilderPass.portstmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        GraphBuilderPass.portDecl_return portDecl105 =null;

        GraphBuilderPass.portDeclInit_return portDeclInit106 =null;



        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:260:3: ( portDecl | portDeclInit )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==PORT_DECL) ) {
                alt16=1;
            }
            else if ( (LA16_0==PORT_INIT) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:260:5: portDecl
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_portDecl_in_portstmt929);
                    portDecl105=portDecl();

                    state._fsp--;

                    adaptor.addChild(root_0, portDecl105.getTree());


                    }
                    break;
                case 2 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:261:5: portDeclInit
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_portDeclInit_in_portstmt936);
                    portDeclInit106=portDeclInit();

                    state._fsp--;

                    adaptor.addChild(root_0, portDeclInit106.getTree());


                    }
                    break;

            }
            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "portstmt"


    public static class portName_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "portName"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:264:1: portName : IDENT ;
    public final GraphBuilderPass.portName_return portName() throws RecognitionException {
        GraphBuilderPass.portName_return retval = new GraphBuilderPass.portName_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree IDENT107=null;

        CommonTree IDENT107_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:265:3: ( IDENT )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:265:5: IDENT
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            IDENT107=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_portName953); 
            IDENT107_tree = (CommonTree)adaptor.dupNode(IDENT107);


            adaptor.addChild(root_0, IDENT107_tree);


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "portName"


    public static class portType_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "portType"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:268:1: portType : ( 'port' | 'eval' );
    public final GraphBuilderPass.portType_return portType() throws RecognitionException {
        GraphBuilderPass.portType_return retval = new GraphBuilderPass.portType_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree set108=null;

        CommonTree set108_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:268:9: ( 'port' | 'eval' )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            set108=(CommonTree)input.LT(1);

            if ( input.LA(1)==50||input.LA(1)==53 ) {
                input.consume();
                set108_tree = (CommonTree)adaptor.dupNode(set108);


                adaptor.addChild(root_0, set108_tree);

                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

             

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "portType"


    public static class mfCall_return extends TreeRuleReturnScope {
        public String name;
        public int line;
        public int pos;
        public List<Expression> expressions;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "mfCall"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:272:1: mfCall[Scope sc] returns [String name, int line, int pos, List<Expression> expressions] : ^( mfName mfparams[sc] ) ;
    public final GraphBuilderPass.mfCall_return mfCall(Scope sc) throws RecognitionException {
        GraphBuilderPass.mfCall_return retval = new GraphBuilderPass.mfCall_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        GraphBuilderPass.mfName_return mfName109 =null;

        GraphBuilderPass.mfparams_return mfparams110 =null;



        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:273:3: ( ^( mfName mfparams[sc] ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:273:5: ^( mfName mfparams[sc] )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_mfName_in_mfCall994);
            mfName109=mfName();

            state._fsp--;

            root_1 = (CommonTree)adaptor.becomeRoot(mfName109.getTree(), root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_mfparams_in_mfCall996);
            mfparams110=mfparams(sc);

            state._fsp--;

            adaptor.addChild(root_1, mfparams110.getTree());


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }


            retval.name = (mfName109!=null?mfName109.name:null);
            retval.line = (mfName109!=null?mfName109.line:0);
            retval.pos = (mfName109!=null?mfName109.pos:0);
            retval.expressions = (mfparams110!=null?mfparams110.expressions:null);


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "mfCall"


    public static class mfName_return extends TreeRuleReturnScope {
        public String name;
        public int line;
        public int pos;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "mfName"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:281:1: mfName returns [String name, int line, int pos] : i= IDENT ;
    public final GraphBuilderPass.mfName_return mfName() throws RecognitionException {
        GraphBuilderPass.mfName_return retval = new GraphBuilderPass.mfName_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree i=null;

        CommonTree i_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:282:3: (i= IDENT )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:282:5: i= IDENT
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_mfName1023); 
            i_tree = (CommonTree)adaptor.dupNode(i);


            adaptor.addChild(root_0, i_tree);


             retval.name = (i!=null?i.getText():null); retval.line = (i!=null?i.getLine():0); retval.pos = (i!=null?i.getCharPositionInLine():0); 

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "mfName"


    public static class mfparams_return extends TreeRuleReturnScope {
        public List<Expression> expressions;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "mfparams"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:285:1: mfparams[Scope sc] returns [List<Expression> expressions] : (exps= expression[sc] )+ ;
    public final GraphBuilderPass.mfparams_return mfparams(Scope sc) throws RecognitionException {
        GraphBuilderPass.mfparams_return retval = new GraphBuilderPass.mfparams_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        GraphBuilderPass.expression_return exps =null;




        retval.expressions = new ArrayList<Expression>();

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:289:3: ( (exps= expression[sc] )+ )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:289:5: (exps= expression[sc] )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:289:5: (exps= expression[sc] )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0 >= NUMBER && LA17_0 <= PATH)||LA17_0==STRING_LITERAL||LA17_0==29||(LA17_0 >= 32 && LA17_0 <= 33)||LA17_0==35||LA17_0==38||LA17_0==58) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:289:6: exps= expression[sc]
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_expression_in_mfparams1051);
            	    exps=expression(sc);

            	    state._fsp--;

            	    adaptor.addChild(root_0, exps.getTree());


            	    retval.expressions.add((exps!=null?exps.exp:null));

            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "mfparams"


    public static class path_return extends TreeRuleReturnScope {
        public Path p;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "path"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:292:1: path[Scope sc] returns [Path p] : ^( PATH (id= IDENT )+ ( pathIndex )? ) ;
    public final GraphBuilderPass.path_return path(Scope sc) throws RecognitionException {
        GraphBuilderPass.path_return retval = new GraphBuilderPass.path_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree id=null;
        CommonTree PATH111=null;
        GraphBuilderPass.pathIndex_return pathIndex112 =null;


        CommonTree id_tree=null;
        CommonTree PATH111_tree=null;


        List<String> parts = new ArrayList<String>();
        boolean hasPathIndex = false;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:297:3: ( ^( PATH (id= IDENT )+ ( pathIndex )? ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:297:5: ^( PATH (id= IDENT )+ ( pathIndex )? )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            PATH111=(CommonTree)match(input,PATH,FOLLOW_PATH_in_path1081); 
            PATH111_tree = (CommonTree)adaptor.dupNode(PATH111);


            root_1 = (CommonTree)adaptor.becomeRoot(PATH111_tree, root_1);


            match(input, Token.DOWN, null); 
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:297:12: (id= IDENT )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==IDENT) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:297:13: id= IDENT
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    id=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_path1086); 
            	    id_tree = (CommonTree)adaptor.dupNode(id);


            	    adaptor.addChild(root_1, id_tree);


            	     parts.add((id!=null?id.getText():null)); 

            	    }
            	    break;

            	default :
            	    if ( cnt18 >= 1 ) break loop18;
                        EarlyExitException eee =
                            new EarlyExitException(18, input);
                        throw eee;
                }
                cnt18++;
            } while (true);


            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:297:48: ( pathIndex )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==PORT_INDEX) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:297:49: pathIndex
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_pathIndex_in_path1092);
                    pathIndex112=pathIndex();

                    state._fsp--;

                    adaptor.addChild(root_1, pathIndex112.getTree());


                     hasPathIndex = true; 

                    }
                    break;

            }


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }



            if(hasPathIndex){
              if ((pathIndex112!=null?pathIndex112.index:0) >=0 ){
                retval.p = new Path(sc, parts, (pathIndex112!=null?pathIndex112.index:0));
              }else if((pathIndex112!=null?pathIndex112.pathKey:null) != ""){
                retval.p = new Path(sc, parts, (pathIndex112!=null?pathIndex112.pathKey:null));
              }
            }else{
              retval.p = new Path(sc, parts);
            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "path"


    public static class pathIndex_return extends TreeRuleReturnScope {
        public String pathKey;
        public int index;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pathIndex"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:311:1: pathIndex returns [String pathKey, int index] : ^( PORT_INDEX portIndex ) ;
    public final GraphBuilderPass.pathIndex_return pathIndex() throws RecognitionException {
        GraphBuilderPass.pathIndex_return retval = new GraphBuilderPass.pathIndex_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree PORT_INDEX113=null;
        GraphBuilderPass.portIndex_return portIndex114 =null;


        CommonTree PORT_INDEX113_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:312:3: ( ^( PORT_INDEX portIndex ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:312:4: ^( PORT_INDEX portIndex )
            {
            root_0 = (CommonTree)adaptor.nil();


            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();
            _last = (CommonTree)input.LT(1);
            PORT_INDEX113=(CommonTree)match(input,PORT_INDEX,FOLLOW_PORT_INDEX_in_pathIndex1118); 
            PORT_INDEX113_tree = (CommonTree)adaptor.dupNode(PORT_INDEX113);


            root_1 = (CommonTree)adaptor.becomeRoot(PORT_INDEX113_tree, root_1);


            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_portIndex_in_pathIndex1120);
            portIndex114=portIndex();

            state._fsp--;

            adaptor.addChild(root_1, portIndex114.getTree());


            match(input, Token.UP, null); 
            adaptor.addChild(root_0, root_1);
            _last = _save_last_1;
            }



            retval.pathKey = (portIndex114!=null?portIndex114.pathKey:null);
            retval.index = (portIndex114!=null?portIndex114.index:0);


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "pathIndex"


    public static class portIndex_return extends TreeRuleReturnScope {
        public String pathKey;
        public int index;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "portIndex"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:319:1: portIndex returns [String pathKey, int index] : ( NUMBER | STRING_LITERAL ) ;
    public final GraphBuilderPass.portIndex_return portIndex() throws RecognitionException {
        GraphBuilderPass.portIndex_return retval = new GraphBuilderPass.portIndex_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree NUMBER115=null;
        CommonTree STRING_LITERAL116=null;

        CommonTree NUMBER115_tree=null;
        CommonTree STRING_LITERAL116_tree=null;


        retval.index = -1;
        retval.pathKey = "";

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:324:3: ( ( NUMBER | STRING_LITERAL ) )
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:324:5: ( NUMBER | STRING_LITERAL )
            {
            root_0 = (CommonTree)adaptor.nil();


            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:324:5: ( NUMBER | STRING_LITERAL )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==NUMBER) ) {
                alt20=1;
            }
            else if ( (LA20_0==STRING_LITERAL) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }
            switch (alt20) {
                case 1 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:324:7: NUMBER
                    {
                    _last = (CommonTree)input.LT(1);
                    NUMBER115=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_portIndex1148); 
                    NUMBER115_tree = (CommonTree)adaptor.dupNode(NUMBER115);


                    adaptor.addChild(root_0, NUMBER115_tree);


                    retval.index = Integer.parseInt((NUMBER115!=null?NUMBER115.getText():null));

                    }
                    break;
                case 2 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:325:6: STRING_LITERAL
                    {
                    _last = (CommonTree)input.LT(1);
                    STRING_LITERAL116=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_portIndex1157); 
                    STRING_LITERAL116_tree = (CommonTree)adaptor.dupNode(STRING_LITERAL116);


                    adaptor.addChild(root_0, STRING_LITERAL116_tree);


                    retval.pathKey = (STRING_LITERAL116!=null?STRING_LITERAL116.getText():null).replace("\"","");

                    }
                    break;

            }


            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "portIndex"


    public static class expression_return extends TreeRuleReturnScope {
        public Expression exp;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expression"
    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:330:1: expression[Scope sc] returns [Expression exp] : ( ^( '+' op1= expression[sc] op2= expression[sc] ) | ^( '-' op1= expression[sc] op2= expression[sc] ) | ^( '*' op1= expression[sc] op2= expression[sc] ) | ^( '/' op1= expression[sc] op2= expression[sc] ) | ^( '%' op1= expression[sc] op2= expression[sc] ) | ^( '|' op1= expression[sc] op2= expression[sc] ) | NUMBER | STRING_LITERAL | path[sc] );
    public final GraphBuilderPass.expression_return expression(Scope sc) throws RecognitionException {
        GraphBuilderPass.expression_return retval = new GraphBuilderPass.expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree char_literal117=null;
        CommonTree char_literal118=null;
        CommonTree char_literal119=null;
        CommonTree char_literal120=null;
        CommonTree char_literal121=null;
        CommonTree char_literal122=null;
        CommonTree NUMBER123=null;
        CommonTree STRING_LITERAL124=null;
        GraphBuilderPass.expression_return op1 =null;

        GraphBuilderPass.expression_return op2 =null;

        GraphBuilderPass.path_return path125 =null;


        CommonTree char_literal117_tree=null;
        CommonTree char_literal118_tree=null;
        CommonTree char_literal119_tree=null;
        CommonTree char_literal120_tree=null;
        CommonTree char_literal121_tree=null;
        CommonTree char_literal122_tree=null;
        CommonTree NUMBER123_tree=null;
        CommonTree STRING_LITERAL124_tree=null;

        try {
            // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:331:3: ( ^( '+' op1= expression[sc] op2= expression[sc] ) | ^( '-' op1= expression[sc] op2= expression[sc] ) | ^( '*' op1= expression[sc] op2= expression[sc] ) | ^( '/' op1= expression[sc] op2= expression[sc] ) | ^( '%' op1= expression[sc] op2= expression[sc] ) | ^( '|' op1= expression[sc] op2= expression[sc] ) | NUMBER | STRING_LITERAL | path[sc] )
            int alt21=9;
            switch ( input.LA(1) ) {
            case 33:
                {
                alt21=1;
                }
                break;
            case 35:
                {
                alt21=2;
                }
                break;
            case 32:
                {
                alt21=3;
                }
                break;
            case 38:
                {
                alt21=4;
                }
                break;
            case 29:
                {
                alt21=5;
                }
                break;
            case 58:
                {
                alt21=6;
                }
                break;
            case NUMBER:
                {
                alt21=7;
                }
                break;
            case STRING_LITERAL:
                {
                alt21=8;
                }
                break;
            case PATH:
                {
                alt21=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }

            switch (alt21) {
                case 1 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:331:5: ^( '+' op1= expression[sc] op2= expression[sc] )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    char_literal117=(CommonTree)match(input,33,FOLLOW_33_in_expression1187); 
                    char_literal117_tree = (CommonTree)adaptor.dupNode(char_literal117);


                    root_1 = (CommonTree)adaptor.becomeRoot(char_literal117_tree, root_1);


                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expression_in_expression1191);
                    op1=expression(sc);

                    state._fsp--;

                    adaptor.addChild(root_1, op1.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expression_in_expression1196);
                    op2=expression(sc);

                    state._fsp--;

                    adaptor.addChild(root_1, op2.getTree());


                    match(input, Token.UP, null); 
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    retval.exp = new Add((op1!=null?op1.exp:null), (op2!=null?op2.exp:null));

                    }
                    break;
                case 2 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:332:5: ^( '-' op1= expression[sc] op2= expression[sc] )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    char_literal118=(CommonTree)match(input,35,FOLLOW_35_in_expression1207); 
                    char_literal118_tree = (CommonTree)adaptor.dupNode(char_literal118);


                    root_1 = (CommonTree)adaptor.becomeRoot(char_literal118_tree, root_1);


                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expression_in_expression1211);
                    op1=expression(sc);

                    state._fsp--;

                    adaptor.addChild(root_1, op1.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expression_in_expression1216);
                    op2=expression(sc);

                    state._fsp--;

                    adaptor.addChild(root_1, op2.getTree());


                    match(input, Token.UP, null); 
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    retval.exp = new Subtract((op1!=null?op1.exp:null), (op2!=null?op2.exp:null));

                    }
                    break;
                case 3 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:333:5: ^( '*' op1= expression[sc] op2= expression[sc] )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    char_literal119=(CommonTree)match(input,32,FOLLOW_32_in_expression1227); 
                    char_literal119_tree = (CommonTree)adaptor.dupNode(char_literal119);


                    root_1 = (CommonTree)adaptor.becomeRoot(char_literal119_tree, root_1);


                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expression_in_expression1231);
                    op1=expression(sc);

                    state._fsp--;

                    adaptor.addChild(root_1, op1.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expression_in_expression1236);
                    op2=expression(sc);

                    state._fsp--;

                    adaptor.addChild(root_1, op2.getTree());


                    match(input, Token.UP, null); 
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    retval.exp = new Multiply((op1!=null?op1.exp:null), (op2!=null?op2.exp:null));

                    }
                    break;
                case 4 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:334:5: ^( '/' op1= expression[sc] op2= expression[sc] )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    char_literal120=(CommonTree)match(input,38,FOLLOW_38_in_expression1247); 
                    char_literal120_tree = (CommonTree)adaptor.dupNode(char_literal120);


                    root_1 = (CommonTree)adaptor.becomeRoot(char_literal120_tree, root_1);


                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expression_in_expression1251);
                    op1=expression(sc);

                    state._fsp--;

                    adaptor.addChild(root_1, op1.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expression_in_expression1256);
                    op2=expression(sc);

                    state._fsp--;

                    adaptor.addChild(root_1, op2.getTree());


                    match(input, Token.UP, null); 
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    retval.exp = new Divide((op1!=null?op1.exp:null), (op2!=null?op2.exp:null));

                    }
                    break;
                case 5 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:335:5: ^( '%' op1= expression[sc] op2= expression[sc] )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    char_literal121=(CommonTree)match(input,29,FOLLOW_29_in_expression1267); 
                    char_literal121_tree = (CommonTree)adaptor.dupNode(char_literal121);


                    root_1 = (CommonTree)adaptor.becomeRoot(char_literal121_tree, root_1);


                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expression_in_expression1271);
                    op1=expression(sc);

                    state._fsp--;

                    adaptor.addChild(root_1, op1.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expression_in_expression1276);
                    op2=expression(sc);

                    state._fsp--;

                    adaptor.addChild(root_1, op2.getTree());


                    match(input, Token.UP, null); 
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    retval.exp = new Mod((op1!=null?op1.exp:null), (op2!=null?op2.exp:null));

                    }
                    break;
                case 6 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:336:5: ^( '|' op1= expression[sc] op2= expression[sc] )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    _last = (CommonTree)input.LT(1);
                    char_literal122=(CommonTree)match(input,58,FOLLOW_58_in_expression1287); 
                    char_literal122_tree = (CommonTree)adaptor.dupNode(char_literal122);


                    root_1 = (CommonTree)adaptor.becomeRoot(char_literal122_tree, root_1);


                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expression_in_expression1291);
                    op1=expression(sc);

                    state._fsp--;

                    adaptor.addChild(root_1, op1.getTree());


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expression_in_expression1296);
                    op2=expression(sc);

                    state._fsp--;

                    adaptor.addChild(root_1, op2.getTree());


                    match(input, Token.UP, null); 
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                    }


                    retval.exp = new Or((op1!=null?op1.exp:null), (op2!=null?op2.exp:null));

                    }
                    break;
                case 7 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:337:5: NUMBER
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    NUMBER123=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_expression1306); 
                    NUMBER123_tree = (CommonTree)adaptor.dupNode(NUMBER123);


                    adaptor.addChild(root_0, NUMBER123_tree);


                    retval.exp = new shiro.expressions.Number(Float.parseFloat((NUMBER123!=null?NUMBER123.getText():null)));

                    }
                    break;
                case 8 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:338:5: STRING_LITERAL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    STRING_LITERAL124=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_expression1314); 
                    STRING_LITERAL124_tree = (CommonTree)adaptor.dupNode(STRING_LITERAL124);


                    adaptor.addChild(root_0, STRING_LITERAL124_tree);


                    retval.exp = new SString((STRING_LITERAL124!=null?STRING_LITERAL124.getText():null).replace("\"",""));

                    }
                    break;
                case 9 :
                    // /Users/jeffreyguenther/Development/SubjDepGraph/src/shiro/interpreter/GraphBuilderPass.g:339:7: path[sc]
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_path_in_expression1324);
                    path125=path(sc);

                    state._fsp--;

                    adaptor.addChild(root_0, path125.getTree());


                    retval.exp = (path125!=null?path125.p:null);

                    }
                    break;

            }
            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expression"

    // Delegated rules


 

    public static final BitSet FOLLOW_statement_in_shiro60 = new BitSet(new long[]{0x0309000000802002L});
    public static final BitSet FOLLOW_nodestmt_in_statement77 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sNode_in_statement83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_graphDecl_in_statement89 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statestmt_in_statement95 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_collection_in_statement101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_view_in_statement107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_view122 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_view124 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_mfName_in_view126 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_view128 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_48_in_collection145 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_collection147 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_orderingFunc_in_collection149 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_path_in_collection151 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_collItem_in_collection154 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_IDENT_in_collItem166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_orderingFunc179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATE_DECL_in_statestmt195 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stateHeader_in_statestmt197 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_stateTimeStmt_in_stateHeader216 = new BitSet(new long[]{0x00001E0000000012L});
    public static final BitSet FOLLOW_stateCommentStmt_in_stateHeader220 = new BitSet(new long[]{0x00001E0000000012L});
    public static final BitSet FOLLOW_stateParentStmt_in_stateHeader224 = new BitSet(new long[]{0x00001E0000000012L});
    public static final BitSet FOLLOW_stateGraphStmt_in_stateHeader227 = new BitSet(new long[]{0x00001E0000000012L});
    public static final BitSet FOLLOW_activation_in_stateHeader230 = new BitSet(new long[]{0x00001E0000000012L});
    public static final BitSet FOLLOW_44_in_stateTimeStmt251 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_time_in_stateTimeStmt253 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_41_in_stateCommentStmt268 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_comment_in_stateCommentStmt270 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_43_in_stateParentStmt289 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stateParent_in_stateParentStmt291 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_42_in_stateGraphStmt308 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stateGraph_in_stateGraphStmt310 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IDENT_in_stateName326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_time340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_comment355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_stateParent370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_stateGraph385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_activation_in_activationPath404 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_37_in_activationPath407 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_activation_in_activationPath413 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_activationList_in_activationPath417 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_ACTIVATION_LIST_in_activationList439 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_activation_in_activationList441 = new BitSet(new long[]{0x0000000000000018L});
    public static final BitSet FOLLOW_ACTIVATION_in_activation463 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_activation467 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_IDENT_in_activation497 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_51_in_graphDecl522 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_graphDecl524 = new BitSet(new long[]{0x0000001000020000L});
    public static final BitSet FOLLOW_graphLine_in_graphDecl526 = new BitSet(new long[]{0x0000001000020008L});
    public static final BitSet FOLLOW_nodeProduction_in_graphLine545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portAssignment_in_graphLine552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nodeProduction_in_nodeInternal568 = new BitSet(new long[]{0x0100001000163002L});
    public static final BitSet FOLLOW_portAssignment_in_nodeInternal572 = new BitSet(new long[]{0x0100001000163002L});
    public static final BitSet FOLLOW_portstmt_in_nodeInternal576 = new BitSet(new long[]{0x0100001000163002L});
    public static final BitSet FOLLOW_nodestmt_in_nodeInternal580 = new BitSet(new long[]{0x0100001000163002L});
    public static final BitSet FOLLOW_sNode_in_nodeInternal584 = new BitSet(new long[]{0x0100001000163002L});
    public static final BitSet FOLLOW_NEWLINE_in_nodeInternal588 = new BitSet(new long[]{0x0100001000163002L});
    public static final BitSet FOLLOW_NODE_STMT_in_nodestmt608 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_nodestmt610 = new BitSet(new long[]{0x0100001000163100L});
    public static final BitSet FOLLOW_activeSelector_in_nodestmt612 = new BitSet(new long[]{0x0100001000163000L});
    public static final BitSet FOLLOW_nodeInternal_in_nodestmt615 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_56_in_sNode631 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_sNode634 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_sNode636 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_subjunctSelector_in_sNode639 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_sNode641 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_sNode644 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_NEWLINE_in_sNode647 = new BitSet(new long[]{0x0080000002001000L});
    public static final BitSet FOLLOW_subjunctDeclNodeProd_in_sNode655 = new BitSet(new long[]{0x0082000002001000L});
    public static final BitSet FOLLOW_subjunctDecl_in_sNode659 = new BitSet(new long[]{0x0082000002001000L});
    public static final BitSet FOLLOW_NEWLINE_in_sNode663 = new BitSet(new long[]{0x0082000002001000L});
    public static final BitSet FOLLOW_49_in_sNode672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUBJ_NODE_PROD_in_subjunctDeclNodeProd689 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_subjunctDeclNodeProd691 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_subjunctDeclNodeProd693 = new BitSet(new long[]{0x0100001000163000L});
    public static final BitSet FOLLOW_nodeInternal_in_subjunctDeclNodeProd695 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_55_in_subjunctDecl712 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_subjunctDecl715 = new BitSet(new long[]{0x0000A00000000000L});
    public static final BitSet FOLLOW_45_in_subjunctDecl718 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_activeSelector_in_subjunctDecl721 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_subjunctDecl723 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_subjunctDecl728 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_NEWLINE_in_subjunctDecl731 = new BitSet(new long[]{0x0100001000163000L});
    public static final BitSet FOLLOW_nodeInternal_in_subjunctDecl738 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_49_in_subjunctDecl744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUBJ_SELECT_in_subjunctSelector759 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_subjunctSelector761 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EVAL_SELECT_in_activeSelector780 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_activeSelector782 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_36_in_nodeProduction801 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_path_in_nodeProduction803 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_activationPath_in_nodeProduction807 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_36_in_nodeProduction818 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_nodeProduction_in_nodeProduction822 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_activationPath_in_nodeProduction824 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PORT_ASSIGNMENT_in_portAssignment847 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_path_in_portAssignment849 = new BitSet(new long[]{0x0400004B21018000L});
    public static final BitSet FOLLOW_mfparams_in_portAssignment856 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PORT_DECL_in_portDecl875 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_PORT_TAG_in_portDecl878 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_portType_in_portDecl880 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_portName_in_portDecl883 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_mfName_in_portDecl885 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PORT_INIT_in_portDeclInit902 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_PORT_TAG_in_portDeclInit905 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_portType_in_portDeclInit907 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_portName_in_portDeclInit910 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_mfCall_in_portDeclInit912 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_portDecl_in_portstmt929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portDeclInit_in_portstmt936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_portName953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mfName_in_mfCall994 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_mfparams_in_mfCall996 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IDENT_in_mfName1023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_mfparams1051 = new BitSet(new long[]{0x0400004B21018002L});
    public static final BitSet FOLLOW_PATH_in_path1081 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_path1086 = new BitSet(new long[]{0x0000000000080208L});
    public static final BitSet FOLLOW_pathIndex_in_path1092 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PORT_INDEX_in_pathIndex1118 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_portIndex_in_pathIndex1120 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NUMBER_in_portIndex1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_portIndex1157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_expression1187 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1191 = new BitSet(new long[]{0x0400004B21018000L});
    public static final BitSet FOLLOW_expression_in_expression1196 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_35_in_expression1207 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1211 = new BitSet(new long[]{0x0400004B21018000L});
    public static final BitSet FOLLOW_expression_in_expression1216 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_32_in_expression1227 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1231 = new BitSet(new long[]{0x0400004B21018000L});
    public static final BitSet FOLLOW_expression_in_expression1236 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_38_in_expression1247 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1251 = new BitSet(new long[]{0x0400004B21018000L});
    public static final BitSet FOLLOW_expression_in_expression1256 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_29_in_expression1267 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1271 = new BitSet(new long[]{0x0400004B21018000L});
    public static final BitSet FOLLOW_expression_in_expression1276 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_58_in_expression1287 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1291 = new BitSet(new long[]{0x0400004B21018000L});
    public static final BitSet FOLLOW_expression_in_expression1296 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NUMBER_in_expression1306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_expression1314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_path_in_expression1324 = new BitSet(new long[]{0x0000000000000002L});

}