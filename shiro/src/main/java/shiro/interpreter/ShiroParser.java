// $ANTLR 3.4 /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g 2012-11-16 16:23:25

package shiro.interpreter;
import java.util.HashMap;
import java.util.Map;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class ShiroParser extends Parser {
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
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public ShiroParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public ShiroParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return ShiroParser.tokenNames; }
    public String getGrammarFileName() { return "/home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g"; }


    // store the AST from the node definitions
    private Map<String, CommonTree> defs = new HashMap<String, CommonTree>();

    public Map<String, CommonTree> getNodeDefinitions(){
        return defs;
    }	



    public static class shiro_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "shiro"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:49:1: shiro : ( statement )+ ;
    public final ShiroParser.shiro_return shiro() throws RecognitionException {
        ShiroParser.shiro_return retval = new ShiroParser.shiro_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        ShiroParser.statement_return statement1 =null;



        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:49:8: ( ( statement )+ )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:49:11: ( statement )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:49:11: ( statement )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==NEWLINE||LA1_0==48||(LA1_0 >= 51 && LA1_0 <= 52)||(LA1_0 >= 54 && LA1_0 <= 57)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:49:11: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_shiro124);
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

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "shiro"


    public static class statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "statement"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:52:1: statement : ( nodestmt | sNode | graphDecl | statestmt | collection | view | NEWLINE !);
    public final ShiroParser.statement_return statement() throws RecognitionException {
        ShiroParser.statement_return retval = new ShiroParser.statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token NEWLINE8=null;
        ShiroParser.nodestmt_return nodestmt2 =null;

        ShiroParser.sNode_return sNode3 =null;

        ShiroParser.graphDecl_return graphDecl4 =null;

        ShiroParser.statestmt_return statestmt5 =null;

        ShiroParser.collection_return collection6 =null;

        ShiroParser.view_return view7 =null;


        CommonTree NEWLINE8_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:53:2: ( nodestmt | sNode | graphDecl | statestmt | collection | view | NEWLINE !)
            int alt2=7;
            switch ( input.LA(1) ) {
            case 52:
            case 55:
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
            case 54:
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
            case NEWLINE:
                {
                alt2=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:53:4: nodestmt
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_nodestmt_in_statement138);
                    nodestmt2=nodestmt();

                    state._fsp--;

                    adaptor.addChild(root_0, nodestmt2.getTree());

                    }
                    break;
                case 2 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:54:4: sNode
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_sNode_in_statement143);
                    sNode3=sNode();

                    state._fsp--;

                    adaptor.addChild(root_0, sNode3.getTree());

                    }
                    break;
                case 3 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:55:4: graphDecl
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_graphDecl_in_statement149);
                    graphDecl4=graphDecl();

                    state._fsp--;

                    adaptor.addChild(root_0, graphDecl4.getTree());

                    }
                    break;
                case 4 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:56:4: statestmt
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_statestmt_in_statement154);
                    statestmt5=statestmt();

                    state._fsp--;

                    adaptor.addChild(root_0, statestmt5.getTree());

                    }
                    break;
                case 5 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:57:4: collection
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_collection_in_statement159);
                    collection6=collection();

                    state._fsp--;

                    adaptor.addChild(root_0, collection6.getTree());

                    }
                    break;
                case 6 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:58:4: view
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_view_in_statement164);
                    view7=view();

                    state._fsp--;

                    adaptor.addChild(root_0, view7.getTree());

                    }
                    break;
                case 7 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:59:4: NEWLINE !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    NEWLINE8=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_statement169); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "statement"


    public static class view_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "view"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:62:1: view : 'view' IDENT mfName IDENT -> ^( 'view' IDENT mfName IDENT ) ;
    public final ShiroParser.view_return view() throws RecognitionException {
        ShiroParser.view_return retval = new ShiroParser.view_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal9=null;
        Token IDENT10=null;
        Token IDENT12=null;
        ShiroParser.mfName_return mfName11 =null;


        CommonTree string_literal9_tree=null;
        CommonTree IDENT10_tree=null;
        CommonTree IDENT12_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_57=new RewriteRuleTokenStream(adaptor,"token 57");
        RewriteRuleSubtreeStream stream_mfName=new RewriteRuleSubtreeStream(adaptor,"rule mfName");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:62:6: ( 'view' IDENT mfName IDENT -> ^( 'view' IDENT mfName IDENT ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:62:8: 'view' IDENT mfName IDENT
            {
            string_literal9=(Token)match(input,57,FOLLOW_57_in_view181);  
            stream_57.add(string_literal9);


            IDENT10=(Token)match(input,IDENT,FOLLOW_IDENT_in_view183);  
            stream_IDENT.add(IDENT10);


            pushFollow(FOLLOW_mfName_in_view185);
            mfName11=mfName();

            state._fsp--;

            stream_mfName.add(mfName11.getTree());

            IDENT12=(Token)match(input,IDENT,FOLLOW_IDENT_in_view187);  
            stream_IDENT.add(IDENT12);


            // AST REWRITE
            // elements: IDENT, 57, IDENT, mfName
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 62:34: -> ^( 'view' IDENT mfName IDENT )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:62:37: ^( 'view' IDENT mfName IDENT )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_57.nextNode()
                , root_1);

                adaptor.addChild(root_1, 
                stream_IDENT.nextNode()
                );

                adaptor.addChild(root_1, stream_mfName.nextTree());

                adaptor.addChild(root_1, 
                stream_IDENT.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "view"


    public static class collection_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "collection"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:65:1: collection : 'collection' IDENT orderingFunc path 'begin' NEWLINE ( collItem )+ ( NEWLINE )? 'end' -> ^( 'collection' IDENT orderingFunc path ( collItem )+ ) ;
    public final ShiroParser.collection_return collection() throws RecognitionException {
        ShiroParser.collection_return retval = new ShiroParser.collection_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal13=null;
        Token IDENT14=null;
        Token string_literal17=null;
        Token NEWLINE18=null;
        Token NEWLINE20=null;
        Token string_literal21=null;
        ShiroParser.orderingFunc_return orderingFunc15 =null;

        ShiroParser.path_return path16 =null;

        ShiroParser.collItem_return collItem19 =null;


        CommonTree string_literal13_tree=null;
        CommonTree IDENT14_tree=null;
        CommonTree string_literal17_tree=null;
        CommonTree NEWLINE18_tree=null;
        CommonTree NEWLINE20_tree=null;
        CommonTree string_literal21_tree=null;
        RewriteRuleTokenStream stream_49=new RewriteRuleTokenStream(adaptor,"token 49");
        RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleSubtreeStream stream_orderingFunc=new RewriteRuleSubtreeStream(adaptor,"rule orderingFunc");
        RewriteRuleSubtreeStream stream_path=new RewriteRuleSubtreeStream(adaptor,"rule path");
        RewriteRuleSubtreeStream stream_collItem=new RewriteRuleSubtreeStream(adaptor,"rule collItem");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:66:2: ( 'collection' IDENT orderingFunc path 'begin' NEWLINE ( collItem )+ ( NEWLINE )? 'end' -> ^( 'collection' IDENT orderingFunc path ( collItem )+ ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:66:4: 'collection' IDENT orderingFunc path 'begin' NEWLINE ( collItem )+ ( NEWLINE )? 'end'
            {
            string_literal13=(Token)match(input,48,FOLLOW_48_in_collection211);  
            stream_48.add(string_literal13);


            IDENT14=(Token)match(input,IDENT,FOLLOW_IDENT_in_collection213);  
            stream_IDENT.add(IDENT14);


            pushFollow(FOLLOW_orderingFunc_in_collection215);
            orderingFunc15=orderingFunc();

            state._fsp--;

            stream_orderingFunc.add(orderingFunc15.getTree());

            pushFollow(FOLLOW_path_in_collection217);
            path16=path();

            state._fsp--;

            stream_path.add(path16.getTree());

            string_literal17=(Token)match(input,47,FOLLOW_47_in_collection219);  
            stream_47.add(string_literal17);


            NEWLINE18=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_collection221);  
            stream_NEWLINE.add(NEWLINE18);


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:67:4: ( collItem )+
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
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:67:5: collItem
            	    {
            	    pushFollow(FOLLOW_collItem_in_collection227);
            	    collItem19=collItem();

            	    state._fsp--;

            	    stream_collItem.add(collItem19.getTree());

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


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:67:16: ( NEWLINE )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==NEWLINE) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:67:16: NEWLINE
                    {
                    NEWLINE20=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_collection231);  
                    stream_NEWLINE.add(NEWLINE20);


                    }
                    break;

            }


            string_literal21=(Token)match(input,49,FOLLOW_49_in_collection236);  
            stream_49.add(string_literal21);


            // AST REWRITE
            // elements: path, IDENT, collItem, 48, orderingFunc
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 69:3: -> ^( 'collection' IDENT orderingFunc path ( collItem )+ )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:69:6: ^( 'collection' IDENT orderingFunc path ( collItem )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_48.nextNode()
                , root_1);

                adaptor.addChild(root_1, 
                stream_IDENT.nextNode()
                );

                adaptor.addChild(root_1, stream_orderingFunc.nextTree());

                adaptor.addChild(root_1, stream_path.nextTree());

                if ( !(stream_collItem.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_collItem.hasNext() ) {
                    adaptor.addChild(root_1, stream_collItem.nextTree());

                }
                stream_collItem.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "collection"


    public static class collItem_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "collItem"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:72:1: collItem : IDENT -> IDENT ;
    public final ShiroParser.collItem_return collItem() throws RecognitionException {
        ShiroParser.collItem_return retval = new ShiroParser.collItem_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token IDENT22=null;

        CommonTree IDENT22_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:72:9: ( IDENT -> IDENT )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:72:11: IDENT
            {
            IDENT22=(Token)match(input,IDENT,FOLLOW_IDENT_in_collItem262);  
            stream_IDENT.add(IDENT22);


            // AST REWRITE
            // elements: IDENT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 72:17: -> IDENT
            {
                adaptor.addChild(root_0, 
                stream_IDENT.nextNode()
                );

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "collItem"


    public static class orderingFunc_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "orderingFunc"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:75:1: orderingFunc : IDENT -> IDENT ;
    public final ShiroParser.orderingFunc_return orderingFunc() throws RecognitionException {
        ShiroParser.orderingFunc_return retval = new ShiroParser.orderingFunc_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token IDENT23=null;

        CommonTree IDENT23_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:76:2: ( IDENT -> IDENT )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:76:4: IDENT
            {
            IDENT23=(Token)match(input,IDENT,FOLLOW_IDENT_in_orderingFunc277);  
            stream_IDENT.add(IDENT23);


            // AST REWRITE
            // elements: IDENT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 76:10: -> IDENT
            {
                adaptor.addChild(root_0, 
                stream_IDENT.nextNode()
                );

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "orderingFunc"


    public static class statestmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "statestmt"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:79:1: statestmt : 'state' stateName 'begin' NEWLINE stateHeader 'end' -> ^( STATE_DECL stateName stateHeader ) ;
    public final ShiroParser.statestmt_return statestmt() throws RecognitionException {
        ShiroParser.statestmt_return retval = new ShiroParser.statestmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal24=null;
        Token string_literal26=null;
        Token NEWLINE27=null;
        Token string_literal29=null;
        ShiroParser.stateName_return stateName25 =null;

        ShiroParser.stateHeader_return stateHeader28 =null;


        CommonTree string_literal24_tree=null;
        CommonTree string_literal26_tree=null;
        CommonTree NEWLINE27_tree=null;
        CommonTree string_literal29_tree=null;
        RewriteRuleTokenStream stream_49=new RewriteRuleTokenStream(adaptor,"token 49");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_54=new RewriteRuleTokenStream(adaptor,"token 54");
        RewriteRuleSubtreeStream stream_stateName=new RewriteRuleSubtreeStream(adaptor,"rule stateName");
        RewriteRuleSubtreeStream stream_stateHeader=new RewriteRuleSubtreeStream(adaptor,"rule stateHeader");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:80:2: ( 'state' stateName 'begin' NEWLINE stateHeader 'end' -> ^( STATE_DECL stateName stateHeader ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:80:4: 'state' stateName 'begin' NEWLINE stateHeader 'end'
            {
            string_literal24=(Token)match(input,54,FOLLOW_54_in_statestmt293);  
            stream_54.add(string_literal24);


            pushFollow(FOLLOW_stateName_in_statestmt295);
            stateName25=stateName();

            state._fsp--;

            stream_stateName.add(stateName25.getTree());

            string_literal26=(Token)match(input,47,FOLLOW_47_in_statestmt297);  
            stream_47.add(string_literal26);


            NEWLINE27=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_statestmt299);  
            stream_NEWLINE.add(NEWLINE27);


            pushFollow(FOLLOW_stateHeader_in_statestmt303);
            stateHeader28=stateHeader();

            state._fsp--;

            stream_stateHeader.add(stateHeader28.getTree());

            string_literal29=(Token)match(input,49,FOLLOW_49_in_statestmt307);  
            stream_49.add(string_literal29);


            // AST REWRITE
            // elements: stateName, stateHeader
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 82:9: -> ^( STATE_DECL stateName stateHeader )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:82:12: ^( STATE_DECL stateName stateHeader )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(STATE_DECL, "STATE_DECL")
                , root_1);

                adaptor.addChild(root_1, stream_stateName.nextTree());

                adaptor.addChild(root_1, stream_stateHeader.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "statestmt"


    public static class stateHeader_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateHeader"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:85:1: stateHeader : ( stateTimeStmt | stateCommentStmt | stateParentStmt | stateGraphStmt | activationPath | NEWLINE !)+ ;
    public final ShiroParser.stateHeader_return stateHeader() throws RecognitionException {
        ShiroParser.stateHeader_return retval = new ShiroParser.stateHeader_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token NEWLINE35=null;
        ShiroParser.stateTimeStmt_return stateTimeStmt30 =null;

        ShiroParser.stateCommentStmt_return stateCommentStmt31 =null;

        ShiroParser.stateParentStmt_return stateParentStmt32 =null;

        ShiroParser.stateGraphStmt_return stateGraphStmt33 =null;

        ShiroParser.activationPath_return activationPath34 =null;


        CommonTree NEWLINE35_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:86:2: ( ( stateTimeStmt | stateCommentStmt | stateParentStmt | stateGraphStmt | activationPath | NEWLINE !)+ )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:86:5: ( stateTimeStmt | stateCommentStmt | stateParentStmt | stateGraphStmt | activationPath | NEWLINE !)+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:86:5: ( stateTimeStmt | stateCommentStmt | stateParentStmt | stateGraphStmt | activationPath | NEWLINE !)+
            int cnt5=0;
            loop5:
            do {
                int alt5=7;
                switch ( input.LA(1) ) {
                case 44:
                    {
                    alt5=1;
                    }
                    break;
                case 41:
                    {
                    alt5=2;
                    }
                    break;
                case 43:
                    {
                    alt5=3;
                    }
                    break;
                case 42:
                    {
                    alt5=4;
                    }
                    break;
                case IDENT:
                    {
                    alt5=5;
                    }
                    break;
                case NEWLINE:
                    {
                    alt5=6;
                    }
                    break;

                }

                switch (alt5) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:86:6: stateTimeStmt
            	    {
            	    pushFollow(FOLLOW_stateTimeStmt_in_stateHeader331);
            	    stateTimeStmt30=stateTimeStmt();

            	    state._fsp--;

            	    adaptor.addChild(root_0, stateTimeStmt30.getTree());

            	    }
            	    break;
            	case 2 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:86:22: stateCommentStmt
            	    {
            	    pushFollow(FOLLOW_stateCommentStmt_in_stateHeader335);
            	    stateCommentStmt31=stateCommentStmt();

            	    state._fsp--;

            	    adaptor.addChild(root_0, stateCommentStmt31.getTree());

            	    }
            	    break;
            	case 3 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:86:41: stateParentStmt
            	    {
            	    pushFollow(FOLLOW_stateParentStmt_in_stateHeader339);
            	    stateParentStmt32=stateParentStmt();

            	    state._fsp--;

            	    adaptor.addChild(root_0, stateParentStmt32.getTree());

            	    }
            	    break;
            	case 4 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:86:59: stateGraphStmt
            	    {
            	    pushFollow(FOLLOW_stateGraphStmt_in_stateHeader343);
            	    stateGraphStmt33=stateGraphStmt();

            	    state._fsp--;

            	    adaptor.addChild(root_0, stateGraphStmt33.getTree());

            	    }
            	    break;
            	case 5 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:86:76: activationPath
            	    {
            	    pushFollow(FOLLOW_activationPath_in_stateHeader347);
            	    activationPath34=activationPath();

            	    state._fsp--;

            	    adaptor.addChild(root_0, activationPath34.getTree());

            	    }
            	    break;
            	case 6 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:86:93: NEWLINE !
            	    {
            	    NEWLINE35=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_stateHeader351); 

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateHeader"


    public static class stateTimeStmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateTimeStmt"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:89:1: stateTimeStmt : 'Time' time -> ^( 'Time' time ) ;
    public final ShiroParser.stateTimeStmt_return stateTimeStmt() throws RecognitionException {
        ShiroParser.stateTimeStmt_return retval = new ShiroParser.stateTimeStmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal36=null;
        ShiroParser.time_return time37 =null;


        CommonTree string_literal36_tree=null;
        RewriteRuleTokenStream stream_44=new RewriteRuleTokenStream(adaptor,"token 44");
        RewriteRuleSubtreeStream stream_time=new RewriteRuleSubtreeStream(adaptor,"rule time");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:90:2: ( 'Time' time -> ^( 'Time' time ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:90:4: 'Time' time
            {
            string_literal36=(Token)match(input,44,FOLLOW_44_in_stateTimeStmt368);  
            stream_44.add(string_literal36);


            pushFollow(FOLLOW_time_in_stateTimeStmt370);
            time37=time();

            state._fsp--;

            stream_time.add(time37.getTree());

            // AST REWRITE
            // elements: time, 44
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 90:16: -> ^( 'Time' time )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:90:19: ^( 'Time' time )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_44.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_time.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateTimeStmt"


    public static class stateCommentStmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateCommentStmt"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:93:1: stateCommentStmt : 'Comment' comment -> ^( 'Comment' comment ) ;
    public final ShiroParser.stateCommentStmt_return stateCommentStmt() throws RecognitionException {
        ShiroParser.stateCommentStmt_return retval = new ShiroParser.stateCommentStmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal38=null;
        ShiroParser.comment_return comment39 =null;


        CommonTree string_literal38_tree=null;
        RewriteRuleTokenStream stream_41=new RewriteRuleTokenStream(adaptor,"token 41");
        RewriteRuleSubtreeStream stream_comment=new RewriteRuleSubtreeStream(adaptor,"rule comment");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:94:2: ( 'Comment' comment -> ^( 'Comment' comment ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:94:4: 'Comment' comment
            {
            string_literal38=(Token)match(input,41,FOLLOW_41_in_stateCommentStmt389);  
            stream_41.add(string_literal38);


            pushFollow(FOLLOW_comment_in_stateCommentStmt391);
            comment39=comment();

            state._fsp--;

            stream_comment.add(comment39.getTree());

            // AST REWRITE
            // elements: comment, 41
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 94:22: -> ^( 'Comment' comment )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:94:25: ^( 'Comment' comment )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_41.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_comment.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateCommentStmt"


    public static class stateParentStmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateParentStmt"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:97:1: stateParentStmt : 'Parent' stateParent -> ^( 'Parent' stateParent ) ;
    public final ShiroParser.stateParentStmt_return stateParentStmt() throws RecognitionException {
        ShiroParser.stateParentStmt_return retval = new ShiroParser.stateParentStmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal40=null;
        ShiroParser.stateParent_return stateParent41 =null;


        CommonTree string_literal40_tree=null;
        RewriteRuleTokenStream stream_43=new RewriteRuleTokenStream(adaptor,"token 43");
        RewriteRuleSubtreeStream stream_stateParent=new RewriteRuleSubtreeStream(adaptor,"rule stateParent");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:98:2: ( 'Parent' stateParent -> ^( 'Parent' stateParent ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:98:4: 'Parent' stateParent
            {
            string_literal40=(Token)match(input,43,FOLLOW_43_in_stateParentStmt412);  
            stream_43.add(string_literal40);


            pushFollow(FOLLOW_stateParent_in_stateParentStmt414);
            stateParent41=stateParent();

            state._fsp--;

            stream_stateParent.add(stateParent41.getTree());

            // AST REWRITE
            // elements: stateParent, 43
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 98:25: -> ^( 'Parent' stateParent )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:98:28: ^( 'Parent' stateParent )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_43.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_stateParent.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateParentStmt"


    public static class stateGraphStmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateGraphStmt"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:101:1: stateGraphStmt : 'Graph' stateGraph -> ^( 'Graph' stateGraph ) ;
    public final ShiroParser.stateGraphStmt_return stateGraphStmt() throws RecognitionException {
        ShiroParser.stateGraphStmt_return retval = new ShiroParser.stateGraphStmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal42=null;
        ShiroParser.stateGraph_return stateGraph43 =null;


        CommonTree string_literal42_tree=null;
        RewriteRuleTokenStream stream_42=new RewriteRuleTokenStream(adaptor,"token 42");
        RewriteRuleSubtreeStream stream_stateGraph=new RewriteRuleSubtreeStream(adaptor,"rule stateGraph");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:102:2: ( 'Graph' stateGraph -> ^( 'Graph' stateGraph ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:102:4: 'Graph' stateGraph
            {
            string_literal42=(Token)match(input,42,FOLLOW_42_in_stateGraphStmt434);  
            stream_42.add(string_literal42);


            pushFollow(FOLLOW_stateGraph_in_stateGraphStmt436);
            stateGraph43=stateGraph();

            state._fsp--;

            stream_stateGraph.add(stateGraph43.getTree());

            // AST REWRITE
            // elements: 42, stateGraph
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 102:23: -> ^( 'Graph' stateGraph )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:102:26: ^( 'Graph' stateGraph )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_42.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_stateGraph.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateGraphStmt"


    public static class stateName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateName"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:105:1: stateName : IDENT ;
    public final ShiroParser.stateName_return stateName() throws RecognitionException {
        ShiroParser.stateName_return retval = new ShiroParser.stateName_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token IDENT44=null;

        CommonTree IDENT44_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:106:2: ( IDENT )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:106:4: IDENT
            {
            root_0 = (CommonTree)adaptor.nil();


            IDENT44=(Token)match(input,IDENT,FOLLOW_IDENT_in_stateName456); 
            IDENT44_tree = 
            (CommonTree)adaptor.create(IDENT44)
            ;
            adaptor.addChild(root_0, IDENT44_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateName"


    public static class time_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "time"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:109:1: time : STRING_LITERAL ;
    public final ShiroParser.time_return time() throws RecognitionException {
        ShiroParser.time_return retval = new ShiroParser.time_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token STRING_LITERAL45=null;

        CommonTree STRING_LITERAL45_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:109:6: ( STRING_LITERAL )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:109:8: STRING_LITERAL
            {
            root_0 = (CommonTree)adaptor.nil();


            STRING_LITERAL45=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_time467); 
            STRING_LITERAL45_tree = 
            (CommonTree)adaptor.create(STRING_LITERAL45)
            ;
            adaptor.addChild(root_0, STRING_LITERAL45_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "time"


    public static class comment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "comment"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:112:1: comment : STRING_LITERAL ;
    public final ShiroParser.comment_return comment() throws RecognitionException {
        ShiroParser.comment_return retval = new ShiroParser.comment_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token STRING_LITERAL46=null;

        CommonTree STRING_LITERAL46_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:112:9: ( STRING_LITERAL )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:112:11: STRING_LITERAL
            {
            root_0 = (CommonTree)adaptor.nil();


            STRING_LITERAL46=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_comment479); 
            STRING_LITERAL46_tree = 
            (CommonTree)adaptor.create(STRING_LITERAL46)
            ;
            adaptor.addChild(root_0, STRING_LITERAL46_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "comment"


    public static class stateParent_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateParent"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:115:1: stateParent : IDENT ;
    public final ShiroParser.stateParent_return stateParent() throws RecognitionException {
        ShiroParser.stateParent_return retval = new ShiroParser.stateParent_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token IDENT47=null;

        CommonTree IDENT47_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:116:2: ( IDENT )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:116:4: IDENT
            {
            root_0 = (CommonTree)adaptor.nil();


            IDENT47=(Token)match(input,IDENT,FOLLOW_IDENT_in_stateParent491); 
            IDENT47_tree = 
            (CommonTree)adaptor.create(IDENT47)
            ;
            adaptor.addChild(root_0, IDENT47_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateParent"


    public static class stateGraph_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stateGraph"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:119:1: stateGraph : IDENT ;
    public final ShiroParser.stateGraph_return stateGraph() throws RecognitionException {
        ShiroParser.stateGraph_return retval = new ShiroParser.stateGraph_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token IDENT48=null;

        CommonTree IDENT48_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:120:2: ( IDENT )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:120:4: IDENT
            {
            root_0 = (CommonTree)adaptor.nil();


            IDENT48=(Token)match(input,IDENT,FOLLOW_IDENT_in_stateGraph503); 
            IDENT48_tree = 
            (CommonTree)adaptor.create(IDENT48)
            ;
            adaptor.addChild(root_0, IDENT48_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stateGraph"


    public static class activationPath_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "activationPath"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:123:1: activationPath : l= activation ( '.' ^ (r= activation | activationList ) )* ;
    public final ShiroParser.activationPath_return activationPath() throws RecognitionException {
        ShiroParser.activationPath_return retval = new ShiroParser.activationPath_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal49=null;
        ShiroParser.activation_return l =null;

        ShiroParser.activation_return r =null;

        ShiroParser.activationList_return activationList50 =null;


        CommonTree char_literal49_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:124:2: (l= activation ( '.' ^ (r= activation | activationList ) )* )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:124:4: l= activation ( '.' ^ (r= activation | activationList ) )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_activation_in_activationPath516);
            l=activation();

            state._fsp--;

            adaptor.addChild(root_0, l.getTree());

            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:124:17: ( '.' ^ (r= activation | activationList ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==37) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:124:18: '.' ^ (r= activation | activationList )
            	    {
            	    char_literal49=(Token)match(input,37,FOLLOW_37_in_activationPath519); 
            	    char_literal49_tree = 
            	    (CommonTree)adaptor.create(char_literal49)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(char_literal49_tree, root_0);


            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:124:23: (r= activation | activationList )
            	    int alt6=2;
            	    int LA6_0 = input.LA(1);

            	    if ( (LA6_0==IDENT) ) {
            	        alt6=1;
            	    }
            	    else if ( (LA6_0==39) ) {
            	        alt6=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 6, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt6) {
            	        case 1 :
            	            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:124:24: r= activation
            	            {
            	            pushFollow(FOLLOW_activation_in_activationPath525);
            	            r=activation();

            	            state._fsp--;

            	            adaptor.addChild(root_0, r.getTree());

            	            }
            	            break;
            	        case 2 :
            	            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:124:39: activationList
            	            {
            	            pushFollow(FOLLOW_activationList_in_activationPath529);
            	            activationList50=activationList();

            	            state._fsp--;

            	            adaptor.addChild(root_0, activationList50.getTree());

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "activationPath"


    public static class activationList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "activationList"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:127:1: activationList : '<' activation ( ',' activation )* '>' -> ^( ACTIVATION_LIST ( activation )+ ) ;
    public final ShiroParser.activationList_return activationList() throws RecognitionException {
        ShiroParser.activationList_return retval = new ShiroParser.activationList_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal51=null;
        Token char_literal53=null;
        Token char_literal55=null;
        ShiroParser.activation_return activation52 =null;

        ShiroParser.activation_return activation54 =null;


        CommonTree char_literal51_tree=null;
        CommonTree char_literal53_tree=null;
        CommonTree char_literal55_tree=null;
        RewriteRuleTokenStream stream_40=new RewriteRuleTokenStream(adaptor,"token 40");
        RewriteRuleTokenStream stream_34=new RewriteRuleTokenStream(adaptor,"token 34");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleSubtreeStream stream_activation=new RewriteRuleSubtreeStream(adaptor,"rule activation");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:128:2: ( '<' activation ( ',' activation )* '>' -> ^( ACTIVATION_LIST ( activation )+ ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:128:4: '<' activation ( ',' activation )* '>'
            {
            char_literal51=(Token)match(input,39,FOLLOW_39_in_activationList543);  
            stream_39.add(char_literal51);


            pushFollow(FOLLOW_activation_in_activationList545);
            activation52=activation();

            state._fsp--;

            stream_activation.add(activation52.getTree());

            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:128:19: ( ',' activation )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==34) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:128:20: ',' activation
            	    {
            	    char_literal53=(Token)match(input,34,FOLLOW_34_in_activationList548);  
            	    stream_34.add(char_literal53);


            	    pushFollow(FOLLOW_activation_in_activationList550);
            	    activation54=activation();

            	    state._fsp--;

            	    stream_activation.add(activation54.getTree());

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            char_literal55=(Token)match(input,40,FOLLOW_40_in_activationList554);  
            stream_40.add(char_literal55);


            // AST REWRITE
            // elements: activation
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 128:41: -> ^( ACTIVATION_LIST ( activation )+ )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:128:44: ^( ACTIVATION_LIST ( activation )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(ACTIVATION_LIST, "ACTIVATION_LIST")
                , root_1);

                if ( !(stream_activation.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_activation.hasNext() ) {
                    adaptor.addChild(root_1, stream_activation.nextTree());

                }
                stream_activation.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "activationList"


    public static class activation_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "activation"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:131:1: activation : c= IDENT ( '[' v= IDENT ']' )? -> ^( ACTIVATION $c ( $v)? ) ;
    public final ShiroParser.activation_return activation() throws RecognitionException {
        ShiroParser.activation_return retval = new ShiroParser.activation_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token c=null;
        Token v=null;
        Token char_literal56=null;
        Token char_literal57=null;

        CommonTree c_tree=null;
        CommonTree v_tree=null;
        CommonTree char_literal56_tree=null;
        CommonTree char_literal57_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_45=new RewriteRuleTokenStream(adaptor,"token 45");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:132:2: (c= IDENT ( '[' v= IDENT ']' )? -> ^( ACTIVATION $c ( $v)? ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:132:4: c= IDENT ( '[' v= IDENT ']' )?
            {
            c=(Token)match(input,IDENT,FOLLOW_IDENT_in_activation577);  
            stream_IDENT.add(c);


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:132:12: ( '[' v= IDENT ']' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==45) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:132:13: '[' v= IDENT ']'
                    {
                    char_literal56=(Token)match(input,45,FOLLOW_45_in_activation580);  
                    stream_45.add(char_literal56);


                    v=(Token)match(input,IDENT,FOLLOW_IDENT_in_activation584);  
                    stream_IDENT.add(v);


                    char_literal57=(Token)match(input,46,FOLLOW_46_in_activation586);  
                    stream_46.add(char_literal57);


                    }
                    break;

            }


            // AST REWRITE
            // elements: c, v
            // token labels: v, c
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_v=new RewriteRuleTokenStream(adaptor,"token v",v);
            RewriteRuleTokenStream stream_c=new RewriteRuleTokenStream(adaptor,"token c",c);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 132:31: -> ^( ACTIVATION $c ( $v)? )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:132:34: ^( ACTIVATION $c ( $v)? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(ACTIVATION, "ACTIVATION")
                , root_1);

                adaptor.addChild(root_1, stream_c.nextNode());

                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:132:50: ( $v)?
                if ( stream_v.hasNext() ) {
                    adaptor.addChild(root_1, stream_v.nextNode());

                }
                stream_v.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "activation"


    public static class graphDecl_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "graphDecl"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:135:1: graphDecl : 'graph' IDENT 'begin' NEWLINE ( graphLine )+ 'end' -> ^( 'graph' IDENT ( graphLine )+ ) ;
    public final ShiroParser.graphDecl_return graphDecl() throws RecognitionException {
        ShiroParser.graphDecl_return retval = new ShiroParser.graphDecl_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal58=null;
        Token IDENT59=null;
        Token string_literal60=null;
        Token NEWLINE61=null;
        Token string_literal63=null;
        ShiroParser.graphLine_return graphLine62 =null;


        CommonTree string_literal58_tree=null;
        CommonTree IDENT59_tree=null;
        CommonTree string_literal60_tree=null;
        CommonTree NEWLINE61_tree=null;
        CommonTree string_literal63_tree=null;
        RewriteRuleTokenStream stream_49=new RewriteRuleTokenStream(adaptor,"token 49");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_51=new RewriteRuleTokenStream(adaptor,"token 51");
        RewriteRuleSubtreeStream stream_graphLine=new RewriteRuleSubtreeStream(adaptor,"rule graphLine");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:136:2: ( 'graph' IDENT 'begin' NEWLINE ( graphLine )+ 'end' -> ^( 'graph' IDENT ( graphLine )+ ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:136:4: 'graph' IDENT 'begin' NEWLINE ( graphLine )+ 'end'
            {
            string_literal58=(Token)match(input,51,FOLLOW_51_in_graphDecl615);  
            stream_51.add(string_literal58);


            IDENT59=(Token)match(input,IDENT,FOLLOW_IDENT_in_graphDecl617);  
            stream_IDENT.add(IDENT59);


            string_literal60=(Token)match(input,47,FOLLOW_47_in_graphDecl619);  
            stream_47.add(string_literal60);


            NEWLINE61=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_graphDecl621);  
            stream_NEWLINE.add(NEWLINE61);


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:137:3: ( graphLine )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==IDENT||LA10_0==NEWLINE) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:137:3: graphLine
            	    {
            	    pushFollow(FOLLOW_graphLine_in_graphDecl625);
            	    graphLine62=graphLine();

            	    state._fsp--;

            	    stream_graphLine.add(graphLine62.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


            string_literal63=(Token)match(input,49,FOLLOW_49_in_graphDecl630);  
            stream_49.add(string_literal63);


            // AST REWRITE
            // elements: 51, graphLine, IDENT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 139:3: -> ^( 'graph' IDENT ( graphLine )+ )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:139:6: ^( 'graph' IDENT ( graphLine )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_51.nextNode()
                , root_1);

                adaptor.addChild(root_1, 
                stream_IDENT.nextNode()
                );

                if ( !(stream_graphLine.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_graphLine.hasNext() ) {
                    adaptor.addChild(root_1, stream_graphLine.nextTree());

                }
                stream_graphLine.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "graphDecl"


    public static class graphLine_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "graphLine"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:142:1: graphLine : ( nodeProduction | portAssignment | NEWLINE !);
    public final ShiroParser.graphLine_return graphLine() throws RecognitionException {
        ShiroParser.graphLine_return retval = new ShiroParser.graphLine_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token NEWLINE66=null;
        ShiroParser.nodeProduction_return nodeProduction64 =null;

        ShiroParser.portAssignment_return portAssignment65 =null;


        CommonTree NEWLINE66_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:143:2: ( nodeProduction | portAssignment | NEWLINE !)
            int alt11=3;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:143:4: nodeProduction
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_nodeProduction_in_graphLine655);
                    nodeProduction64=nodeProduction();

                    state._fsp--;

                    adaptor.addChild(root_0, nodeProduction64.getTree());

                    }
                    break;
                case 2 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:143:21: portAssignment
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_portAssignment_in_graphLine659);
                    portAssignment65=portAssignment();

                    state._fsp--;

                    adaptor.addChild(root_0, portAssignment65.getTree());

                    }
                    break;
                case 3 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:143:38: NEWLINE !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    NEWLINE66=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_graphLine663); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "graphLine"


    public static class nodeInternal_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nodeInternal"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:146:1: nodeInternal : ( nodeProduction | portAssignment | portstmt | nodestmt | sNode | NEWLINE !)+ ;
    public final ShiroParser.nodeInternal_return nodeInternal() throws RecognitionException {
        ShiroParser.nodeInternal_return retval = new ShiroParser.nodeInternal_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token NEWLINE72=null;
        ShiroParser.nodeProduction_return nodeProduction67 =null;

        ShiroParser.portAssignment_return portAssignment68 =null;

        ShiroParser.portstmt_return portstmt69 =null;

        ShiroParser.nodestmt_return nodestmt70 =null;

        ShiroParser.sNode_return sNode71 =null;


        CommonTree NEWLINE72_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:147:2: ( ( nodeProduction | portAssignment | portstmt | nodestmt | sNode | NEWLINE !)+ )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:147:4: ( nodeProduction | portAssignment | portstmt | nodestmt | sNode | NEWLINE !)+
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:147:4: ( nodeProduction | portAssignment | portstmt | nodestmt | sNode | NEWLINE !)+
            int cnt12=0;
            loop12:
            do {
                int alt12=7;
                alt12 = dfa12.predict(input);
                switch (alt12) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:147:6: nodeProduction
            	    {
            	    pushFollow(FOLLOW_nodeProduction_in_nodeInternal678);
            	    nodeProduction67=nodeProduction();

            	    state._fsp--;

            	    adaptor.addChild(root_0, nodeProduction67.getTree());

            	    }
            	    break;
            	case 2 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:148:5: portAssignment
            	    {
            	    pushFollow(FOLLOW_portAssignment_in_nodeInternal685);
            	    portAssignment68=portAssignment();

            	    state._fsp--;

            	    adaptor.addChild(root_0, portAssignment68.getTree());

            	    }
            	    break;
            	case 3 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:149:5: portstmt
            	    {
            	    pushFollow(FOLLOW_portstmt_in_nodeInternal692);
            	    portstmt69=portstmt();

            	    state._fsp--;

            	    adaptor.addChild(root_0, portstmt69.getTree());

            	    }
            	    break;
            	case 4 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:150:5: nodestmt
            	    {
            	    pushFollow(FOLLOW_nodestmt_in_nodeInternal698);
            	    nodestmt70=nodestmt();

            	    state._fsp--;

            	    adaptor.addChild(root_0, nodestmt70.getTree());

            	    }
            	    break;
            	case 5 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:151:5: sNode
            	    {
            	    pushFollow(FOLLOW_sNode_in_nodeInternal705);
            	    sNode71=sNode();

            	    state._fsp--;

            	    adaptor.addChild(root_0, sNode71.getTree());

            	    }
            	    break;
            	case 6 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:152:5: NEWLINE !
            	    {
            	    NEWLINE72=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_nodeInternal712); 

            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nodeInternal"


    public static class nodestmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nodestmt"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:155:1: nodestmt : nodeType IDENT ( '[' activeSelector ']' )? 'begin' NEWLINE nodeInternal 'end' -> ^( NODE_STMT IDENT ( activeSelector )? nodeInternal ) ;
    public final ShiroParser.nodestmt_return nodestmt() throws RecognitionException {
        ShiroParser.nodestmt_return retval = new ShiroParser.nodestmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token IDENT74=null;
        Token char_literal75=null;
        Token char_literal77=null;
        Token string_literal78=null;
        Token NEWLINE79=null;
        Token string_literal81=null;
        ShiroParser.nodeType_return nodeType73 =null;

        ShiroParser.activeSelector_return activeSelector76 =null;

        ShiroParser.nodeInternal_return nodeInternal80 =null;


        CommonTree IDENT74_tree=null;
        CommonTree char_literal75_tree=null;
        CommonTree char_literal77_tree=null;
        CommonTree string_literal78_tree=null;
        CommonTree NEWLINE79_tree=null;
        CommonTree string_literal81_tree=null;
        RewriteRuleTokenStream stream_49=new RewriteRuleTokenStream(adaptor,"token 49");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_45=new RewriteRuleTokenStream(adaptor,"token 45");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleSubtreeStream stream_nodeType=new RewriteRuleSubtreeStream(adaptor,"rule nodeType");
        RewriteRuleSubtreeStream stream_activeSelector=new RewriteRuleSubtreeStream(adaptor,"rule activeSelector");
        RewriteRuleSubtreeStream stream_nodeInternal=new RewriteRuleSubtreeStream(adaptor,"rule nodeInternal");

        String nodeName = "";


        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:164:2: ( nodeType IDENT ( '[' activeSelector ']' )? 'begin' NEWLINE nodeInternal 'end' -> ^( NODE_STMT IDENT ( activeSelector )? nodeInternal ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:164:4: nodeType IDENT ( '[' activeSelector ']' )? 'begin' NEWLINE nodeInternal 'end'
            {
            pushFollow(FOLLOW_nodeType_in_nodestmt736);
            nodeType73=nodeType();

            state._fsp--;

            stream_nodeType.add(nodeType73.getTree());

            IDENT74=(Token)match(input,IDENT,FOLLOW_IDENT_in_nodestmt738);  
            stream_IDENT.add(IDENT74);


            nodeName = (IDENT74!=null?IDENT74.getText():null);

            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:164:45: ( '[' activeSelector ']' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==45) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:164:46: '[' activeSelector ']'
                    {
                    char_literal75=(Token)match(input,45,FOLLOW_45_in_nodestmt743);  
                    stream_45.add(char_literal75);


                    pushFollow(FOLLOW_activeSelector_in_nodestmt745);
                    activeSelector76=activeSelector();

                    state._fsp--;

                    stream_activeSelector.add(activeSelector76.getTree());

                    char_literal77=(Token)match(input,46,FOLLOW_46_in_nodestmt747);  
                    stream_46.add(char_literal77);


                    }
                    break;

            }


            string_literal78=(Token)match(input,47,FOLLOW_47_in_nodestmt751);  
            stream_47.add(string_literal78);


            NEWLINE79=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_nodestmt753);  
            stream_NEWLINE.add(NEWLINE79);


            pushFollow(FOLLOW_nodeInternal_in_nodestmt757);
            nodeInternal80=nodeInternal();

            state._fsp--;

            stream_nodeInternal.add(nodeInternal80.getTree());

            string_literal81=(Token)match(input,49,FOLLOW_49_in_nodestmt761);  
            stream_49.add(string_literal81);


            // AST REWRITE
            // elements: activeSelector, IDENT, nodeInternal
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 166:9: -> ^( NODE_STMT IDENT ( activeSelector )? nodeInternal )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:166:12: ^( NODE_STMT IDENT ( activeSelector )? nodeInternal )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(NODE_STMT, "NODE_STMT")
                , root_1);

                adaptor.addChild(root_1, 
                stream_IDENT.nextNode()
                );

                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:166:30: ( activeSelector )?
                if ( stream_activeSelector.hasNext() ) {
                    adaptor.addChild(root_1, stream_activeSelector.nextTree());

                }
                stream_activeSelector.reset();

                adaptor.addChild(root_1, stream_nodeInternal.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            // add the node AST to list of definition
            defs.put(nodeName , ((CommonTree)retval.tree));

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nodestmt"


    public static class nodeType_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nodeType"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:169:1: nodeType : ( 'node' | 'subjunct' );
    public final ShiroParser.nodeType_return nodeType() throws RecognitionException {
        ShiroParser.nodeType_return retval = new ShiroParser.nodeType_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token set82=null;

        CommonTree set82_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:169:9: ( 'node' | 'subjunct' )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            set82=(Token)input.LT(1);

            if ( input.LA(1)==52||input.LA(1)==55 ) {
                input.consume();
                adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(set82)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nodeType"


    public static class sNode_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "sNode"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:172:1: sNode : 'subjunctive node' ^ IDENT '[' ! subjunctSelector ']' ! 'begin' ! NEWLINE ! ( subjunctDeclNodeProd | subjunctDecl | NEWLINE !)+ 'end' !;
    public final ShiroParser.sNode_return sNode() throws RecognitionException {
        ShiroParser.sNode_return retval = new ShiroParser.sNode_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal83=null;
        Token IDENT84=null;
        Token char_literal85=null;
        Token char_literal87=null;
        Token string_literal88=null;
        Token NEWLINE89=null;
        Token NEWLINE92=null;
        Token string_literal93=null;
        ShiroParser.subjunctSelector_return subjunctSelector86 =null;

        ShiroParser.subjunctDeclNodeProd_return subjunctDeclNodeProd90 =null;

        ShiroParser.subjunctDecl_return subjunctDecl91 =null;


        CommonTree string_literal83_tree=null;
        CommonTree IDENT84_tree=null;
        CommonTree char_literal85_tree=null;
        CommonTree char_literal87_tree=null;
        CommonTree string_literal88_tree=null;
        CommonTree NEWLINE89_tree=null;
        CommonTree NEWLINE92_tree=null;
        CommonTree string_literal93_tree=null;


        String nodeName = "";


        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:181:3: ( 'subjunctive node' ^ IDENT '[' ! subjunctSelector ']' ! 'begin' ! NEWLINE ! ( subjunctDeclNodeProd | subjunctDecl | NEWLINE !)+ 'end' !)
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:181:3: 'subjunctive node' ^ IDENT '[' ! subjunctSelector ']' ! 'begin' ! NEWLINE ! ( subjunctDeclNodeProd | subjunctDecl | NEWLINE !)+ 'end' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal83=(Token)match(input,56,FOLLOW_56_in_sNode807); 
            string_literal83_tree = 
            (CommonTree)adaptor.create(string_literal83)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal83_tree, root_0);


            IDENT84=(Token)match(input,IDENT,FOLLOW_IDENT_in_sNode810); 
            IDENT84_tree = 
            (CommonTree)adaptor.create(IDENT84)
            ;
            adaptor.addChild(root_0, IDENT84_tree);


            nodeName = (IDENT84!=null?IDENT84.getText():null);

            char_literal85=(Token)match(input,45,FOLLOW_45_in_sNode813); 

            pushFollow(FOLLOW_subjunctSelector_in_sNode816);
            subjunctSelector86=subjunctSelector();

            state._fsp--;

            adaptor.addChild(root_0, subjunctSelector86.getTree());

            char_literal87=(Token)match(input,46,FOLLOW_46_in_sNode818); 

            string_literal88=(Token)match(input,47,FOLLOW_47_in_sNode821); 

            NEWLINE89=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_sNode824); 

            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:182:3: ( subjunctDeclNodeProd | subjunctDecl | NEWLINE !)+
            int cnt14=0;
            loop14:
            do {
                int alt14=4;
                switch ( input.LA(1) ) {
                case IDENT:
                    {
                    alt14=1;
                    }
                    break;
                case 52:
                case 55:
                    {
                    alt14=2;
                    }
                    break;
                case NEWLINE:
                    {
                    alt14=3;
                    }
                    break;

                }

                switch (alt14) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:182:4: subjunctDeclNodeProd
            	    {
            	    pushFollow(FOLLOW_subjunctDeclNodeProd_in_sNode830);
            	    subjunctDeclNodeProd90=subjunctDeclNodeProd();

            	    state._fsp--;

            	    adaptor.addChild(root_0, subjunctDeclNodeProd90.getTree());

            	    }
            	    break;
            	case 2 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:182:27: subjunctDecl
            	    {
            	    pushFollow(FOLLOW_subjunctDecl_in_sNode834);
            	    subjunctDecl91=subjunctDecl();

            	    state._fsp--;

            	    adaptor.addChild(root_0, subjunctDecl91.getTree());

            	    }
            	    break;
            	case 3 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:182:42: NEWLINE !
            	    {
            	    NEWLINE92=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_sNode838); 

            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);


            string_literal93=(Token)match(input,49,FOLLOW_49_in_sNode845); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            // add the node AST to list of definition
            defs.put(nodeName, ((CommonTree)retval.tree));

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "sNode"


    public static class subjunctDeclNodeProd_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "subjunctDeclNodeProd"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:186:1: subjunctDeclNodeProd : l= IDENT '->' r= IDENT 'begin' NEWLINE nodeInternal 'end' -> ^( SUBJ_NODE_PROD $l $r nodeInternal ) ;
    public final ShiroParser.subjunctDeclNodeProd_return subjunctDeclNodeProd() throws RecognitionException {
        ShiroParser.subjunctDeclNodeProd_return retval = new ShiroParser.subjunctDeclNodeProd_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token l=null;
        Token r=null;
        Token string_literal94=null;
        Token string_literal95=null;
        Token NEWLINE96=null;
        Token string_literal98=null;
        ShiroParser.nodeInternal_return nodeInternal97 =null;


        CommonTree l_tree=null;
        CommonTree r_tree=null;
        CommonTree string_literal94_tree=null;
        CommonTree string_literal95_tree=null;
        CommonTree NEWLINE96_tree=null;
        CommonTree string_literal98_tree=null;
        RewriteRuleTokenStream stream_49=new RewriteRuleTokenStream(adaptor,"token 49");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_36=new RewriteRuleTokenStream(adaptor,"token 36");
        RewriteRuleSubtreeStream stream_nodeInternal=new RewriteRuleSubtreeStream(adaptor,"rule nodeInternal");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:187:2: (l= IDENT '->' r= IDENT 'begin' NEWLINE nodeInternal 'end' -> ^( SUBJ_NODE_PROD $l $r nodeInternal ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:187:4: l= IDENT '->' r= IDENT 'begin' NEWLINE nodeInternal 'end'
            {
            l=(Token)match(input,IDENT,FOLLOW_IDENT_in_subjunctDeclNodeProd860);  
            stream_IDENT.add(l);


            string_literal94=(Token)match(input,36,FOLLOW_36_in_subjunctDeclNodeProd862);  
            stream_36.add(string_literal94);


            r=(Token)match(input,IDENT,FOLLOW_IDENT_in_subjunctDeclNodeProd866);  
            stream_IDENT.add(r);


            string_literal95=(Token)match(input,47,FOLLOW_47_in_subjunctDeclNodeProd868);  
            stream_47.add(string_literal95);


            NEWLINE96=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_subjunctDeclNodeProd870);  
            stream_NEWLINE.add(NEWLINE96);


            pushFollow(FOLLOW_nodeInternal_in_subjunctDeclNodeProd874);
            nodeInternal97=nodeInternal();

            state._fsp--;

            stream_nodeInternal.add(nodeInternal97.getTree());

            string_literal98=(Token)match(input,49,FOLLOW_49_in_subjunctDeclNodeProd878);  
            stream_49.add(string_literal98);


            // AST REWRITE
            // elements: r, nodeInternal, l
            // token labels: r, l
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_r=new RewriteRuleTokenStream(adaptor,"token r",r);
            RewriteRuleTokenStream stream_l=new RewriteRuleTokenStream(adaptor,"token l",l);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 189:9: -> ^( SUBJ_NODE_PROD $l $r nodeInternal )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:189:12: ^( SUBJ_NODE_PROD $l $r nodeInternal )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(SUBJ_NODE_PROD, "SUBJ_NODE_PROD")
                , root_1);

                adaptor.addChild(root_1, stream_l.nextNode());

                adaptor.addChild(root_1, stream_r.nextNode());

                adaptor.addChild(root_1, stream_nodeInternal.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "subjunctDeclNodeProd"


    public static class subjunctDecl_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "subjunctDecl"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:192:1: subjunctDecl : nodestmt ;
    public final ShiroParser.subjunctDecl_return subjunctDecl() throws RecognitionException {
        ShiroParser.subjunctDecl_return retval = new ShiroParser.subjunctDecl_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        ShiroParser.nodestmt_return nodestmt99 =null;



        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:193:2: ( nodestmt )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:193:4: nodestmt
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_nodestmt_in_subjunctDecl904);
            nodestmt99=nodestmt();

            state._fsp--;

            adaptor.addChild(root_0, nodestmt99.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "subjunctDecl"


    public static class subjunctSelector_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "subjunctSelector"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:196:1: subjunctSelector : IDENT -> ^( SUBJ_SELECT IDENT ) ;
    public final ShiroParser.subjunctSelector_return subjunctSelector() throws RecognitionException {
        ShiroParser.subjunctSelector_return retval = new ShiroParser.subjunctSelector_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token IDENT100=null;

        CommonTree IDENT100_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:197:2: ( IDENT -> ^( SUBJ_SELECT IDENT ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:197:4: IDENT
            {
            IDENT100=(Token)match(input,IDENT,FOLLOW_IDENT_in_subjunctSelector916);  
            stream_IDENT.add(IDENT100);


            // AST REWRITE
            // elements: IDENT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 197:10: -> ^( SUBJ_SELECT IDENT )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:197:13: ^( SUBJ_SELECT IDENT )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(SUBJ_SELECT, "SUBJ_SELECT")
                , root_1);

                adaptor.addChild(root_1, 
                stream_IDENT.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "subjunctSelector"


    public static class activeSelector_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "activeSelector"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:200:1: activeSelector : IDENT -> ^( EVAL_SELECT IDENT ) ;
    public final ShiroParser.activeSelector_return activeSelector() throws RecognitionException {
        ShiroParser.activeSelector_return retval = new ShiroParser.activeSelector_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token IDENT101=null;

        CommonTree IDENT101_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:201:2: ( IDENT -> ^( EVAL_SELECT IDENT ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:201:4: IDENT
            {
            IDENT101=(Token)match(input,IDENT,FOLLOW_IDENT_in_activeSelector937);  
            stream_IDENT.add(IDENT101);


            // AST REWRITE
            // elements: IDENT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 201:10: -> ^( EVAL_SELECT IDENT )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:201:13: ^( EVAL_SELECT IDENT )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(EVAL_SELECT, "EVAL_SELECT")
                , root_1);

                adaptor.addChild(root_1, 
                stream_IDENT.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "activeSelector"


    public static class nodeProduction_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nodeProduction"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:204:1: nodeProduction : path ( '->' ^ activationPath )+ NEWLINE !;
    public final ShiroParser.nodeProduction_return nodeProduction() throws RecognitionException {
        ShiroParser.nodeProduction_return retval = new ShiroParser.nodeProduction_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal103=null;
        Token NEWLINE105=null;
        ShiroParser.path_return path102 =null;

        ShiroParser.activationPath_return activationPath104 =null;


        CommonTree string_literal103_tree=null;
        CommonTree NEWLINE105_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:205:2: ( path ( '->' ^ activationPath )+ NEWLINE !)
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:205:4: path ( '->' ^ activationPath )+ NEWLINE !
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_path_in_nodeProduction956);
            path102=path();

            state._fsp--;

            adaptor.addChild(root_0, path102.getTree());

            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:205:9: ( '->' ^ activationPath )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==36) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:205:10: '->' ^ activationPath
            	    {
            	    string_literal103=(Token)match(input,36,FOLLOW_36_in_nodeProduction959); 
            	    string_literal103_tree = 
            	    (CommonTree)adaptor.create(string_literal103)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(string_literal103_tree, root_0);


            	    pushFollow(FOLLOW_activationPath_in_nodeProduction962);
            	    activationPath104=activationPath();

            	    state._fsp--;

            	    adaptor.addChild(root_0, activationPath104.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);


            NEWLINE105=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_nodeProduction967); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nodeProduction"


    public static class portAssignment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "portAssignment"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:208:1: portAssignment : path '(' mfparams ')' NEWLINE -> ^( PORT_ASSIGNMENT path mfparams ) ;
    public final ShiroParser.portAssignment_return portAssignment() throws RecognitionException {
        ShiroParser.portAssignment_return retval = new ShiroParser.portAssignment_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal107=null;
        Token char_literal109=null;
        Token NEWLINE110=null;
        ShiroParser.path_return path106 =null;

        ShiroParser.mfparams_return mfparams108 =null;


        CommonTree char_literal107_tree=null;
        CommonTree char_literal109_tree=null;
        CommonTree NEWLINE110_tree=null;
        RewriteRuleTokenStream stream_30=new RewriteRuleTokenStream(adaptor,"token 30");
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_31=new RewriteRuleTokenStream(adaptor,"token 31");
        RewriteRuleSubtreeStream stream_path=new RewriteRuleSubtreeStream(adaptor,"rule path");
        RewriteRuleSubtreeStream stream_mfparams=new RewriteRuleSubtreeStream(adaptor,"rule mfparams");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:209:2: ( path '(' mfparams ')' NEWLINE -> ^( PORT_ASSIGNMENT path mfparams ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:209:4: path '(' mfparams ')' NEWLINE
            {
            pushFollow(FOLLOW_path_in_portAssignment979);
            path106=path();

            state._fsp--;

            stream_path.add(path106.getTree());

            char_literal107=(Token)match(input,30,FOLLOW_30_in_portAssignment981);  
            stream_30.add(char_literal107);


            pushFollow(FOLLOW_mfparams_in_portAssignment983);
            mfparams108=mfparams();

            state._fsp--;

            stream_mfparams.add(mfparams108.getTree());

            char_literal109=(Token)match(input,31,FOLLOW_31_in_portAssignment985);  
            stream_31.add(char_literal109);


            NEWLINE110=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_portAssignment987);  
            stream_NEWLINE.add(NEWLINE110);


            // AST REWRITE
            // elements: path, mfparams
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 209:34: -> ^( PORT_ASSIGNMENT path mfparams )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:209:37: ^( PORT_ASSIGNMENT path mfparams )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(PORT_ASSIGNMENT, "PORT_ASSIGNMENT")
                , root_1);

                adaptor.addChild(root_1, stream_path.nextTree());

                adaptor.addChild(root_1, stream_mfparams.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "portAssignment"


    public static class portDecl_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "portDecl"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:212:1: portDecl : portType portName mfName -> ^( PORT_DECL ^( PORT_TAG portType ) portName mfName ) ;
    public final ShiroParser.portDecl_return portDecl() throws RecognitionException {
        ShiroParser.portDecl_return retval = new ShiroParser.portDecl_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        ShiroParser.portType_return portType111 =null;

        ShiroParser.portName_return portName112 =null;

        ShiroParser.mfName_return mfName113 =null;


        RewriteRuleSubtreeStream stream_portType=new RewriteRuleSubtreeStream(adaptor,"rule portType");
        RewriteRuleSubtreeStream stream_mfName=new RewriteRuleSubtreeStream(adaptor,"rule mfName");
        RewriteRuleSubtreeStream stream_portName=new RewriteRuleSubtreeStream(adaptor,"rule portName");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:213:2: ( portType portName mfName -> ^( PORT_DECL ^( PORT_TAG portType ) portName mfName ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:213:4: portType portName mfName
            {
            pushFollow(FOLLOW_portType_in_portDecl1009);
            portType111=portType();

            state._fsp--;

            stream_portType.add(portType111.getTree());

            pushFollow(FOLLOW_portName_in_portDecl1011);
            portName112=portName();

            state._fsp--;

            stream_portName.add(portName112.getTree());

            pushFollow(FOLLOW_mfName_in_portDecl1013);
            mfName113=mfName();

            state._fsp--;

            stream_mfName.add(mfName113.getTree());

            // AST REWRITE
            // elements: portName, portType, mfName
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 213:29: -> ^( PORT_DECL ^( PORT_TAG portType ) portName mfName )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:213:32: ^( PORT_DECL ^( PORT_TAG portType ) portName mfName )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(PORT_DECL, "PORT_DECL")
                , root_1);

                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:213:44: ^( PORT_TAG portType )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(PORT_TAG, "PORT_TAG")
                , root_2);

                adaptor.addChild(root_2, stream_portType.nextTree());

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_1, stream_portName.nextTree());

                adaptor.addChild(root_1, stream_mfName.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "portDecl"


    public static class portDeclInit_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "portDeclInit"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:216:1: portDeclInit : portType portName mfCall -> ^( PORT_INIT ^( PORT_TAG portType ) portName mfCall ) ;
    public final ShiroParser.portDeclInit_return portDeclInit() throws RecognitionException {
        ShiroParser.portDeclInit_return retval = new ShiroParser.portDeclInit_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        ShiroParser.portType_return portType114 =null;

        ShiroParser.portName_return portName115 =null;

        ShiroParser.mfCall_return mfCall116 =null;


        RewriteRuleSubtreeStream stream_portType=new RewriteRuleSubtreeStream(adaptor,"rule portType");
        RewriteRuleSubtreeStream stream_mfCall=new RewriteRuleSubtreeStream(adaptor,"rule mfCall");
        RewriteRuleSubtreeStream stream_portName=new RewriteRuleSubtreeStream(adaptor,"rule portName");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:217:2: ( portType portName mfCall -> ^( PORT_INIT ^( PORT_TAG portType ) portName mfCall ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:217:4: portType portName mfCall
            {
            pushFollow(FOLLOW_portType_in_portDeclInit1041);
            portType114=portType();

            state._fsp--;

            stream_portType.add(portType114.getTree());

            pushFollow(FOLLOW_portName_in_portDeclInit1043);
            portName115=portName();

            state._fsp--;

            stream_portName.add(portName115.getTree());

            pushFollow(FOLLOW_mfCall_in_portDeclInit1045);
            mfCall116=mfCall();

            state._fsp--;

            stream_mfCall.add(mfCall116.getTree());

            // AST REWRITE
            // elements: mfCall, portType, portName
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 217:29: -> ^( PORT_INIT ^( PORT_TAG portType ) portName mfCall )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:217:32: ^( PORT_INIT ^( PORT_TAG portType ) portName mfCall )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(PORT_INIT, "PORT_INIT")
                , root_1);

                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:217:44: ^( PORT_TAG portType )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(PORT_TAG, "PORT_TAG")
                , root_2);

                adaptor.addChild(root_2, stream_portType.nextTree());

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_1, stream_portName.nextTree());

                adaptor.addChild(root_1, stream_mfCall.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "portDeclInit"


    public static class portstmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "portstmt"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:220:1: portstmt : ( portDecl | portDeclInit ) NEWLINE !;
    public final ShiroParser.portstmt_return portstmt() throws RecognitionException {
        ShiroParser.portstmt_return retval = new ShiroParser.portstmt_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token NEWLINE119=null;
        ShiroParser.portDecl_return portDecl117 =null;

        ShiroParser.portDeclInit_return portDeclInit118 =null;


        CommonTree NEWLINE119_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:221:2: ( ( portDecl | portDeclInit ) NEWLINE !)
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:221:4: ( portDecl | portDeclInit ) NEWLINE !
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:221:4: ( portDecl | portDeclInit )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==50||LA16_0==53) ) {
                int LA16_1 = input.LA(2);

                if ( (LA16_1==IDENT) ) {
                    int LA16_2 = input.LA(3);

                    if ( (LA16_2==IDENT) ) {
                        int LA16_3 = input.LA(4);

                        if ( (LA16_3==NEWLINE) ) {
                            alt16=1;
                        }
                        else if ( (LA16_3==30) ) {
                            alt16=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 16, 3, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:221:5: portDecl
                    {
                    pushFollow(FOLLOW_portDecl_in_portstmt1074);
                    portDecl117=portDecl();

                    state._fsp--;

                    adaptor.addChild(root_0, portDecl117.getTree());

                    }
                    break;
                case 2 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:221:16: portDeclInit
                    {
                    pushFollow(FOLLOW_portDeclInit_in_portstmt1078);
                    portDeclInit118=portDeclInit();

                    state._fsp--;

                    adaptor.addChild(root_0, portDeclInit118.getTree());

                    }
                    break;

            }


            NEWLINE119=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_portstmt1082); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "portstmt"


    public static class portName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "portName"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:224:1: portName : IDENT ;
    public final ShiroParser.portName_return portName() throws RecognitionException {
        ShiroParser.portName_return retval = new ShiroParser.portName_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token IDENT120=null;

        CommonTree IDENT120_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:225:2: ( IDENT )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:225:4: IDENT
            {
            root_0 = (CommonTree)adaptor.nil();


            IDENT120=(Token)match(input,IDENT,FOLLOW_IDENT_in_portName1097); 
            IDENT120_tree = 
            (CommonTree)adaptor.create(IDENT120)
            ;
            adaptor.addChild(root_0, IDENT120_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "portName"


    public static class portType_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "portType"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:228:1: portType : ( 'port' | 'eval' );
    public final ShiroParser.portType_return portType() throws RecognitionException {
        ShiroParser.portType_return retval = new ShiroParser.portType_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token set121=null;

        CommonTree set121_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:228:9: ( 'port' | 'eval' )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            set121=(Token)input.LT(1);

            if ( input.LA(1)==50||input.LA(1)==53 ) {
                input.consume();
                adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(set121)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "portType"


    public static class mfCall_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "mfCall"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:232:1: mfCall : mfName '(' mfparams ')' -> ^( mfName mfparams ) ;
    public final ShiroParser.mfCall_return mfCall() throws RecognitionException {
        ShiroParser.mfCall_return retval = new ShiroParser.mfCall_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal123=null;
        Token char_literal125=null;
        ShiroParser.mfName_return mfName122 =null;

        ShiroParser.mfparams_return mfparams124 =null;


        CommonTree char_literal123_tree=null;
        CommonTree char_literal125_tree=null;
        RewriteRuleTokenStream stream_30=new RewriteRuleTokenStream(adaptor,"token 30");
        RewriteRuleTokenStream stream_31=new RewriteRuleTokenStream(adaptor,"token 31");
        RewriteRuleSubtreeStream stream_mfName=new RewriteRuleSubtreeStream(adaptor,"rule mfName");
        RewriteRuleSubtreeStream stream_mfparams=new RewriteRuleSubtreeStream(adaptor,"rule mfparams");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:232:8: ( mfName '(' mfparams ')' -> ^( mfName mfparams ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:232:10: mfName '(' mfparams ')'
            {
            pushFollow(FOLLOW_mfName_in_mfCall1124);
            mfName122=mfName();

            state._fsp--;

            stream_mfName.add(mfName122.getTree());

            char_literal123=(Token)match(input,30,FOLLOW_30_in_mfCall1126);  
            stream_30.add(char_literal123);


            pushFollow(FOLLOW_mfparams_in_mfCall1128);
            mfparams124=mfparams();

            state._fsp--;

            stream_mfparams.add(mfparams124.getTree());

            char_literal125=(Token)match(input,31,FOLLOW_31_in_mfCall1130);  
            stream_31.add(char_literal125);


            // AST REWRITE
            // elements: mfparams, mfName
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 232:34: -> ^( mfName mfparams )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:232:37: ^( mfName mfparams )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_mfName.nextNode(), root_1);

                adaptor.addChild(root_1, stream_mfparams.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "mfCall"


    public static class mfName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "mfName"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:235:1: mfName : IDENT ;
    public final ShiroParser.mfName_return mfName() throws RecognitionException {
        ShiroParser.mfName_return retval = new ShiroParser.mfName_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token IDENT126=null;

        CommonTree IDENT126_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:235:9: ( IDENT )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:235:11: IDENT
            {
            root_0 = (CommonTree)adaptor.nil();


            IDENT126=(Token)match(input,IDENT,FOLLOW_IDENT_in_mfName1150); 
            IDENT126_tree = 
            (CommonTree)adaptor.create(IDENT126)
            ;
            adaptor.addChild(root_0, IDENT126_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "mfName"


    public static class mfparams_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "mfparams"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:238:1: mfparams : expression ( ',' expression )* -> ( expression )+ ;
    public final ShiroParser.mfparams_return mfparams() throws RecognitionException {
        ShiroParser.mfparams_return retval = new ShiroParser.mfparams_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal128=null;
        ShiroParser.expression_return expression127 =null;

        ShiroParser.expression_return expression129 =null;


        CommonTree char_literal128_tree=null;
        RewriteRuleTokenStream stream_34=new RewriteRuleTokenStream(adaptor,"token 34");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:238:9: ( expression ( ',' expression )* -> ( expression )+ )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:238:11: expression ( ',' expression )*
            {
            pushFollow(FOLLOW_expression_in_mfparams1159);
            expression127=expression();

            state._fsp--;

            stream_expression.add(expression127.getTree());

            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:238:21: ( ',' expression )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==34) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:238:22: ',' expression
            	    {
            	    char_literal128=(Token)match(input,34,FOLLOW_34_in_mfparams1161);  
            	    stream_34.add(char_literal128);


            	    pushFollow(FOLLOW_expression_in_mfparams1163);
            	    expression129=expression();

            	    state._fsp--;

            	    stream_expression.add(expression129.getTree());

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            // AST REWRITE
            // elements: expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 238:39: -> ( expression )+
            {
                if ( !(stream_expression.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_expression.hasNext() ) {
                    adaptor.addChild(root_0, stream_expression.nextTree());

                }
                stream_expression.reset();

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "mfparams"


    public static class path_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "path"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:242:1: path : ( IDENT ) ( '.' IDENT )* ( '[' pathIndex ']' )? -> ^( PATH ( IDENT )+ ( pathIndex )? ) ;
    public final ShiroParser.path_return path() throws RecognitionException {
        ShiroParser.path_return retval = new ShiroParser.path_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token IDENT130=null;
        Token char_literal131=null;
        Token IDENT132=null;
        Token char_literal133=null;
        Token char_literal135=null;
        ShiroParser.pathIndex_return pathIndex134 =null;


        CommonTree IDENT130_tree=null;
        CommonTree char_literal131_tree=null;
        CommonTree IDENT132_tree=null;
        CommonTree char_literal133_tree=null;
        CommonTree char_literal135_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_45=new RewriteRuleTokenStream(adaptor,"token 45");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_37=new RewriteRuleTokenStream(adaptor,"token 37");
        RewriteRuleSubtreeStream stream_pathIndex=new RewriteRuleSubtreeStream(adaptor,"rule pathIndex");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:242:7: ( ( IDENT ) ( '.' IDENT )* ( '[' pathIndex ']' )? -> ^( PATH ( IDENT )+ ( pathIndex )? ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:242:9: ( IDENT ) ( '.' IDENT )* ( '[' pathIndex ']' )?
            {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:242:9: ( IDENT )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:242:10: IDENT
            {
            IDENT130=(Token)match(input,IDENT,FOLLOW_IDENT_in_path1183);  
            stream_IDENT.add(IDENT130);


            }


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:242:16: ( '.' IDENT )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==37) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:242:17: '.' IDENT
            	    {
            	    char_literal131=(Token)match(input,37,FOLLOW_37_in_path1186);  
            	    stream_37.add(char_literal131);


            	    IDENT132=(Token)match(input,IDENT,FOLLOW_IDENT_in_path1188);  
            	    stream_IDENT.add(IDENT132);


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:242:28: ( '[' pathIndex ']' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==45) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:242:29: '[' pathIndex ']'
                    {
                    char_literal133=(Token)match(input,45,FOLLOW_45_in_path1192);  
                    stream_45.add(char_literal133);


                    pushFollow(FOLLOW_pathIndex_in_path1194);
                    pathIndex134=pathIndex();

                    state._fsp--;

                    stream_pathIndex.add(pathIndex134.getTree());

                    char_literal135=(Token)match(input,46,FOLLOW_46_in_path1196);  
                    stream_46.add(char_literal135);


                    }
                    break;

            }


            // AST REWRITE
            // elements: pathIndex, IDENT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 242:49: -> ^( PATH ( IDENT )+ ( pathIndex )? )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:242:52: ^( PATH ( IDENT )+ ( pathIndex )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(PATH, "PATH")
                , root_1);

                if ( !(stream_IDENT.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_IDENT.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_IDENT.nextNode()
                    );

                }
                stream_IDENT.reset();

                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:242:66: ( pathIndex )?
                if ( stream_pathIndex.hasNext() ) {
                    adaptor.addChild(root_1, stream_pathIndex.nextTree());

                }
                stream_pathIndex.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "path"


    public static class pathIndex_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pathIndex"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:245:1: pathIndex : portIndex -> ^( PORT_INDEX portIndex ) ;
    public final ShiroParser.pathIndex_return pathIndex() throws RecognitionException {
        ShiroParser.pathIndex_return retval = new ShiroParser.pathIndex_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        ShiroParser.portIndex_return portIndex136 =null;


        RewriteRuleSubtreeStream stream_portIndex=new RewriteRuleSubtreeStream(adaptor,"rule portIndex");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:246:2: ( portIndex -> ^( PORT_INDEX portIndex ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:246:4: portIndex
            {
            pushFollow(FOLLOW_portIndex_in_pathIndex1222);
            portIndex136=portIndex();

            state._fsp--;

            stream_portIndex.add(portIndex136.getTree());

            // AST REWRITE
            // elements: portIndex
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 246:14: -> ^( PORT_INDEX portIndex )
            {
                // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:246:17: ^( PORT_INDEX portIndex )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(PORT_INDEX, "PORT_INDEX")
                , root_1);

                adaptor.addChild(root_1, stream_portIndex.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "pathIndex"


    public static class portIndex_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "portIndex"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:249:1: portIndex : ( NUMBER | STRING_LITERAL ) ;
    public final ShiroParser.portIndex_return portIndex() throws RecognitionException {
        ShiroParser.portIndex_return retval = new ShiroParser.portIndex_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token set137=null;

        CommonTree set137_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:250:2: ( ( NUMBER | STRING_LITERAL ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            set137=(Token)input.LT(1);

            if ( input.LA(1)==NUMBER||input.LA(1)==STRING_LITERAL ) {
                input.consume();
                adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(set137)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "portIndex"


    public static class term_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "term"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:254:1: term : ( path | '(' expression ')' -> expression | NUMBER | STRING_LITERAL );
    public final ShiroParser.term_return term() throws RecognitionException {
        ShiroParser.term_return retval = new ShiroParser.term_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal139=null;
        Token char_literal141=null;
        Token NUMBER142=null;
        Token STRING_LITERAL143=null;
        ShiroParser.path_return path138 =null;

        ShiroParser.expression_return expression140 =null;


        CommonTree char_literal139_tree=null;
        CommonTree char_literal141_tree=null;
        CommonTree NUMBER142_tree=null;
        CommonTree STRING_LITERAL143_tree=null;
        RewriteRuleTokenStream stream_30=new RewriteRuleTokenStream(adaptor,"token 30");
        RewriteRuleTokenStream stream_31=new RewriteRuleTokenStream(adaptor,"token 31");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:254:7: ( path | '(' expression ')' -> expression | NUMBER | STRING_LITERAL )
            int alt20=4;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt20=1;
                }
                break;
            case 30:
                {
                alt20=2;
                }
                break;
            case NUMBER:
                {
                alt20=3;
                }
                break;
            case STRING_LITERAL:
                {
                alt20=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }

            switch (alt20) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:254:9: path
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_path_in_term1261);
                    path138=path();

                    state._fsp--;

                    adaptor.addChild(root_0, path138.getTree());

                    }
                    break;
                case 2 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:255:4: '(' expression ')'
                    {
                    char_literal139=(Token)match(input,30,FOLLOW_30_in_term1266);  
                    stream_30.add(char_literal139);


                    pushFollow(FOLLOW_expression_in_term1268);
                    expression140=expression();

                    state._fsp--;

                    stream_expression.add(expression140.getTree());

                    char_literal141=(Token)match(input,31,FOLLOW_31_in_term1270);  
                    stream_31.add(char_literal141);


                    // AST REWRITE
                    // elements: expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 255:23: -> expression
                    {
                        adaptor.addChild(root_0, stream_expression.nextTree());

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:256:4: NUMBER
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    NUMBER142=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_term1279); 
                    NUMBER142_tree = 
                    (CommonTree)adaptor.create(NUMBER142)
                    ;
                    adaptor.addChild(root_0, NUMBER142_tree);


                    }
                    break;
                case 4 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:257:5: STRING_LITERAL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    STRING_LITERAL143=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_term1285); 
                    STRING_LITERAL143_tree = 
                    (CommonTree)adaptor.create(STRING_LITERAL143)
                    ;
                    adaptor.addChild(root_0, STRING_LITERAL143_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "term"


    public static class unary_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unary"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:260:1: unary : ( '+' ^| '-' ^)* term ;
    public final ShiroParser.unary_return unary() throws RecognitionException {
        ShiroParser.unary_return retval = new ShiroParser.unary_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal144=null;
        Token char_literal145=null;
        ShiroParser.term_return term146 =null;


        CommonTree char_literal144_tree=null;
        CommonTree char_literal145_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:260:8: ( ( '+' ^| '-' ^)* term )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:260:10: ( '+' ^| '-' ^)* term
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:260:10: ( '+' ^| '-' ^)*
            loop21:
            do {
                int alt21=3;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==33) ) {
                    alt21=1;
                }
                else if ( (LA21_0==35) ) {
                    alt21=2;
                }


                switch (alt21) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:260:11: '+' ^
            	    {
            	    char_literal144=(Token)match(input,33,FOLLOW_33_in_unary1298); 
            	    char_literal144_tree = 
            	    (CommonTree)adaptor.create(char_literal144)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(char_literal144_tree, root_0);


            	    }
            	    break;
            	case 2 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:260:18: '-' ^
            	    {
            	    char_literal145=(Token)match(input,35,FOLLOW_35_in_unary1303); 
            	    char_literal145_tree = 
            	    (CommonTree)adaptor.create(char_literal145)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(char_literal145_tree, root_0);


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            pushFollow(FOLLOW_term_in_unary1308);
            term146=term();

            state._fsp--;

            adaptor.addChild(root_0, term146.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "unary"


    public static class mult_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "mult"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:264:1: mult : unary ( ( '*' ^| '/' ^| '%' ^) unary )* ;
    public final ShiroParser.mult_return mult() throws RecognitionException {
        ShiroParser.mult_return retval = new ShiroParser.mult_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal148=null;
        Token char_literal149=null;
        Token char_literal150=null;
        ShiroParser.unary_return unary147 =null;

        ShiroParser.unary_return unary151 =null;


        CommonTree char_literal148_tree=null;
        CommonTree char_literal149_tree=null;
        CommonTree char_literal150_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:264:7: ( unary ( ( '*' ^| '/' ^| '%' ^) unary )* )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:264:9: unary ( ( '*' ^| '/' ^| '%' ^) unary )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_unary_in_mult1322);
            unary147=unary();

            state._fsp--;

            adaptor.addChild(root_0, unary147.getTree());

            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:264:15: ( ( '*' ^| '/' ^| '%' ^) unary )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==29||LA23_0==32||LA23_0==38) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:264:16: ( '*' ^| '/' ^| '%' ^) unary
            	    {
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:264:16: ( '*' ^| '/' ^| '%' ^)
            	    int alt22=3;
            	    switch ( input.LA(1) ) {
            	    case 32:
            	        {
            	        alt22=1;
            	        }
            	        break;
            	    case 38:
            	        {
            	        alt22=2;
            	        }
            	        break;
            	    case 29:
            	        {
            	        alt22=3;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 22, 0, input);

            	        throw nvae;

            	    }

            	    switch (alt22) {
            	        case 1 :
            	            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:264:17: '*' ^
            	            {
            	            char_literal148=(Token)match(input,32,FOLLOW_32_in_mult1326); 
            	            char_literal148_tree = 
            	            (CommonTree)adaptor.create(char_literal148)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal148_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:264:24: '/' ^
            	            {
            	            char_literal149=(Token)match(input,38,FOLLOW_38_in_mult1331); 
            	            char_literal149_tree = 
            	            (CommonTree)adaptor.create(char_literal149)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal149_tree, root_0);


            	            }
            	            break;
            	        case 3 :
            	            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:264:31: '%' ^
            	            {
            	            char_literal150=(Token)match(input,29,FOLLOW_29_in_mult1336); 
            	            char_literal150_tree = 
            	            (CommonTree)adaptor.create(char_literal150)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal150_tree, root_0);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_unary_in_mult1340);
            	    unary151=unary();

            	    state._fsp--;

            	    adaptor.addChild(root_0, unary151.getTree());

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "mult"


    public static class add_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "add"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:267:1: add : mult ( ( '+' ^| '-' ^) mult )* ;
    public final ShiroParser.add_return add() throws RecognitionException {
        ShiroParser.add_return retval = new ShiroParser.add_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal153=null;
        Token char_literal154=null;
        ShiroParser.mult_return mult152 =null;

        ShiroParser.mult_return mult155 =null;


        CommonTree char_literal153_tree=null;
        CommonTree char_literal154_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:268:2: ( mult ( ( '+' ^| '-' ^) mult )* )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:268:4: mult ( ( '+' ^| '-' ^) mult )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_mult_in_add1355);
            mult152=mult();

            state._fsp--;

            adaptor.addChild(root_0, mult152.getTree());

            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:268:9: ( ( '+' ^| '-' ^) mult )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==33||LA25_0==35) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:268:10: ( '+' ^| '-' ^) mult
            	    {
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:268:10: ( '+' ^| '-' ^)
            	    int alt24=2;
            	    int LA24_0 = input.LA(1);

            	    if ( (LA24_0==33) ) {
            	        alt24=1;
            	    }
            	    else if ( (LA24_0==35) ) {
            	        alt24=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 24, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt24) {
            	        case 1 :
            	            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:268:12: '+' ^
            	            {
            	            char_literal153=(Token)match(input,33,FOLLOW_33_in_add1360); 
            	            char_literal153_tree = 
            	            (CommonTree)adaptor.create(char_literal153)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal153_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:268:19: '-' ^
            	            {
            	            char_literal154=(Token)match(input,35,FOLLOW_35_in_add1365); 
            	            char_literal154_tree = 
            	            (CommonTree)adaptor.create(char_literal154)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal154_tree, root_0);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_mult_in_add1370);
            	    mult155=mult();

            	    state._fsp--;

            	    adaptor.addChild(root_0, mult155.getTree());

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "add"


    public static class expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expression"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:271:1: expression : add ( ( '|' ^) add )* ;
    public final ShiroParser.expression_return expression() throws RecognitionException {
        ShiroParser.expression_return retval = new ShiroParser.expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal157=null;
        ShiroParser.add_return add156 =null;

        ShiroParser.add_return add158 =null;


        CommonTree char_literal157_tree=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:272:2: ( add ( ( '|' ^) add )* )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:272:4: add ( ( '|' ^) add )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_add_in_expression1384);
            add156=add();

            state._fsp--;

            adaptor.addChild(root_0, add156.getTree());

            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:272:8: ( ( '|' ^) add )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==58) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:272:9: ( '|' ^) add
            	    {
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:272:9: ( '|' ^)
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/Shiro.g:272:11: '|' ^
            	    {
            	    char_literal157=(Token)match(input,58,FOLLOW_58_in_expression1389); 
            	    char_literal157_tree = 
            	    (CommonTree)adaptor.create(char_literal157)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(char_literal157_tree, root_0);


            	    }


            	    pushFollow(FOLLOW_add_in_expression1394);
            	    add158=add();

            	    state._fsp--;

            	    adaptor.addChild(root_0, add158.getTree());

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expression"

    // Delegated rules


    protected DFA11 dfa11 = new DFA11(this);
    protected DFA12 dfa12 = new DFA12(this);
    static final String DFA11_eotS =
        "\12\uffff";
    static final String DFA11_eofS =
        "\12\uffff";
    static final String DFA11_minS =
        "\1\11\1\36\1\uffff\1\11\1\17\2\uffff\1\36\1\56\1\36";
    static final String DFA11_maxS =
        "\1\14\1\55\1\uffff\1\11\1\30\2\uffff\1\55\1\56\1\44";
    static final String DFA11_acceptS =
        "\2\uffff\1\3\2\uffff\1\1\1\2\3\uffff";
    static final String DFA11_specialS =
        "\12\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\1\2\uffff\1\2",
            "\1\6\5\uffff\1\5\1\3\7\uffff\1\4",
            "",
            "\1\7",
            "\1\10\10\uffff\1\10",
            "",
            "",
            "\1\6\5\uffff\1\5\1\3\7\uffff\1\4",
            "\1\11",
            "\1\6\5\uffff\1\5"
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "142:1: graphLine : ( nodeProduction | portAssignment | NEWLINE !);";
        }
    }
    static final String DFA12_eotS =
        "\16\uffff";
    static final String DFA12_eofS =
        "\16\uffff";
    static final String DFA12_minS =
        "\1\11\1\uffff\1\36\4\uffff\1\11\1\17\2\uffff\1\36\1\56\1\36";
    static final String DFA12_maxS =
        "\1\70\1\uffff\1\55\4\uffff\1\11\1\30\2\uffff\1\55\1\56\1\44";
    static final String DFA12_acceptS =
        "\1\uffff\1\7\1\uffff\1\3\1\4\1\5\1\6\2\uffff\1\1\1\2\3\uffff";
    static final String DFA12_specialS =
        "\16\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\2\2\uffff\1\6\44\uffff\1\1\1\3\1\uffff\1\4\1\3\1\uffff\1"+
            "\4\1\5",
            "",
            "\1\12\5\uffff\1\11\1\7\7\uffff\1\10",
            "",
            "",
            "",
            "",
            "\1\13",
            "\1\14\10\uffff\1\14",
            "",
            "",
            "\1\12\5\uffff\1\11\1\7\7\uffff\1\10",
            "\1\15",
            "\1\12\5\uffff\1\11"
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "()+ loopback of 147:4: ( nodeProduction | portAssignment | portstmt | nodestmt | sNode | NEWLINE !)+";
        }
    }
 

    public static final BitSet FOLLOW_statement_in_shiro124 = new BitSet(new long[]{0x03D9000000001002L});
    public static final BitSet FOLLOW_nodestmt_in_statement138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sNode_in_statement143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_graphDecl_in_statement149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statestmt_in_statement154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_collection_in_statement159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_view_in_statement164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_statement169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_view181 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_view183 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_mfName_in_view185 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_view187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_collection211 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_collection213 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_orderingFunc_in_collection215 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_path_in_collection217 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_collection219 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_NEWLINE_in_collection221 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_collItem_in_collection227 = new BitSet(new long[]{0x0002000000001200L});
    public static final BitSet FOLLOW_NEWLINE_in_collection231 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_49_in_collection236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_collItem262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_orderingFunc277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_statestmt293 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_stateName_in_statestmt295 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_statestmt297 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_NEWLINE_in_statestmt299 = new BitSet(new long[]{0x00001E0000001200L});
    public static final BitSet FOLLOW_stateHeader_in_statestmt303 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_49_in_statestmt307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stateTimeStmt_in_stateHeader331 = new BitSet(new long[]{0x00001E0000001202L});
    public static final BitSet FOLLOW_stateCommentStmt_in_stateHeader335 = new BitSet(new long[]{0x00001E0000001202L});
    public static final BitSet FOLLOW_stateParentStmt_in_stateHeader339 = new BitSet(new long[]{0x00001E0000001202L});
    public static final BitSet FOLLOW_stateGraphStmt_in_stateHeader343 = new BitSet(new long[]{0x00001E0000001202L});
    public static final BitSet FOLLOW_activationPath_in_stateHeader347 = new BitSet(new long[]{0x00001E0000001202L});
    public static final BitSet FOLLOW_NEWLINE_in_stateHeader351 = new BitSet(new long[]{0x00001E0000001202L});
    public static final BitSet FOLLOW_44_in_stateTimeStmt368 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_time_in_stateTimeStmt370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_stateCommentStmt389 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_comment_in_stateCommentStmt391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_stateParentStmt412 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_stateParent_in_stateParentStmt414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_stateGraphStmt434 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_stateGraph_in_stateGraphStmt436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_stateName456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_time467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_comment479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_stateParent491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_stateGraph503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_activation_in_activationPath516 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_37_in_activationPath519 = new BitSet(new long[]{0x0000008000000200L});
    public static final BitSet FOLLOW_activation_in_activationPath525 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_activationList_in_activationPath529 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_39_in_activationList543 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_activation_in_activationList545 = new BitSet(new long[]{0x0000010400000000L});
    public static final BitSet FOLLOW_34_in_activationList548 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_activation_in_activationList550 = new BitSet(new long[]{0x0000010400000000L});
    public static final BitSet FOLLOW_40_in_activationList554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_activation577 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_45_in_activation580 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_activation584 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_activation586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_graphDecl615 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_graphDecl617 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_graphDecl619 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_NEWLINE_in_graphDecl621 = new BitSet(new long[]{0x0000000000001200L});
    public static final BitSet FOLLOW_graphLine_in_graphDecl625 = new BitSet(new long[]{0x0002000000001200L});
    public static final BitSet FOLLOW_49_in_graphDecl630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nodeProduction_in_graphLine655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portAssignment_in_graphLine659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_graphLine663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nodeProduction_in_nodeInternal678 = new BitSet(new long[]{0x01B4000000001202L});
    public static final BitSet FOLLOW_portAssignment_in_nodeInternal685 = new BitSet(new long[]{0x01B4000000001202L});
    public static final BitSet FOLLOW_portstmt_in_nodeInternal692 = new BitSet(new long[]{0x01B4000000001202L});
    public static final BitSet FOLLOW_nodestmt_in_nodeInternal698 = new BitSet(new long[]{0x01B4000000001202L});
    public static final BitSet FOLLOW_sNode_in_nodeInternal705 = new BitSet(new long[]{0x01B4000000001202L});
    public static final BitSet FOLLOW_NEWLINE_in_nodeInternal712 = new BitSet(new long[]{0x01B4000000001202L});
    public static final BitSet FOLLOW_nodeType_in_nodestmt736 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_nodestmt738 = new BitSet(new long[]{0x0000A00000000000L});
    public static final BitSet FOLLOW_45_in_nodestmt743 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_activeSelector_in_nodestmt745 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_nodestmt747 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_nodestmt751 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_NEWLINE_in_nodestmt753 = new BitSet(new long[]{0x01B4000000001200L});
    public static final BitSet FOLLOW_nodeInternal_in_nodestmt757 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_49_in_nodestmt761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_sNode807 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_sNode810 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_sNode813 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_subjunctSelector_in_sNode816 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_sNode818 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_sNode821 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_NEWLINE_in_sNode824 = new BitSet(new long[]{0x0090000000001200L});
    public static final BitSet FOLLOW_subjunctDeclNodeProd_in_sNode830 = new BitSet(new long[]{0x0092000000001200L});
    public static final BitSet FOLLOW_subjunctDecl_in_sNode834 = new BitSet(new long[]{0x0092000000001200L});
    public static final BitSet FOLLOW_NEWLINE_in_sNode838 = new BitSet(new long[]{0x0092000000001200L});
    public static final BitSet FOLLOW_49_in_sNode845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_subjunctDeclNodeProd860 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_subjunctDeclNodeProd862 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_subjunctDeclNodeProd866 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_subjunctDeclNodeProd868 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_NEWLINE_in_subjunctDeclNodeProd870 = new BitSet(new long[]{0x01B4000000001200L});
    public static final BitSet FOLLOW_nodeInternal_in_subjunctDeclNodeProd874 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_49_in_subjunctDeclNodeProd878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nodestmt_in_subjunctDecl904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_subjunctSelector916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_activeSelector937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_path_in_nodeProduction956 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_nodeProduction959 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_activationPath_in_nodeProduction962 = new BitSet(new long[]{0x0000001000001000L});
    public static final BitSet FOLLOW_NEWLINE_in_nodeProduction967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_path_in_portAssignment979 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_portAssignment981 = new BitSet(new long[]{0x0000000A41008200L});
    public static final BitSet FOLLOW_mfparams_in_portAssignment983 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_portAssignment985 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_NEWLINE_in_portAssignment987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portType_in_portDecl1009 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_portName_in_portDecl1011 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_mfName_in_portDecl1013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portType_in_portDeclInit1041 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_portName_in_portDeclInit1043 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_mfCall_in_portDeclInit1045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portDecl_in_portstmt1074 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_portDeclInit_in_portstmt1078 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_NEWLINE_in_portstmt1082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_portName1097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mfName_in_mfCall1124 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_mfCall1126 = new BitSet(new long[]{0x0000000A41008200L});
    public static final BitSet FOLLOW_mfparams_in_mfCall1128 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_mfCall1130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_mfName1150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_mfparams1159 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_34_in_mfparams1161 = new BitSet(new long[]{0x0000000A41008200L});
    public static final BitSet FOLLOW_expression_in_mfparams1163 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_IDENT_in_path1183 = new BitSet(new long[]{0x0000202000000002L});
    public static final BitSet FOLLOW_37_in_path1186 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_path1188 = new BitSet(new long[]{0x0000202000000002L});
    public static final BitSet FOLLOW_45_in_path1192 = new BitSet(new long[]{0x0000000001008000L});
    public static final BitSet FOLLOW_pathIndex_in_path1194 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_path1196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portIndex_in_pathIndex1222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_path_in_term1261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_term1266 = new BitSet(new long[]{0x0000000A41008200L});
    public static final BitSet FOLLOW_expression_in_term1268 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_term1270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_term1279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_term1285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_unary1298 = new BitSet(new long[]{0x0000000A41008200L});
    public static final BitSet FOLLOW_35_in_unary1303 = new BitSet(new long[]{0x0000000A41008200L});
    public static final BitSet FOLLOW_term_in_unary1308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_mult1322 = new BitSet(new long[]{0x0000004120000002L});
    public static final BitSet FOLLOW_32_in_mult1326 = new BitSet(new long[]{0x0000000A41008200L});
    public static final BitSet FOLLOW_38_in_mult1331 = new BitSet(new long[]{0x0000000A41008200L});
    public static final BitSet FOLLOW_29_in_mult1336 = new BitSet(new long[]{0x0000000A41008200L});
    public static final BitSet FOLLOW_unary_in_mult1340 = new BitSet(new long[]{0x0000004120000002L});
    public static final BitSet FOLLOW_mult_in_add1355 = new BitSet(new long[]{0x0000000A00000002L});
    public static final BitSet FOLLOW_33_in_add1360 = new BitSet(new long[]{0x0000000A41008200L});
    public static final BitSet FOLLOW_35_in_add1365 = new BitSet(new long[]{0x0000000A41008200L});
    public static final BitSet FOLLOW_mult_in_add1370 = new BitSet(new long[]{0x0000000A00000002L});
    public static final BitSet FOLLOW_add_in_expression1384 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_58_in_expression1389 = new BitSet(new long[]{0x0000000A41008200L});
    public static final BitSet FOLLOW_add_in_expression1394 = new BitSet(new long[]{0x0400000000000002L});

}