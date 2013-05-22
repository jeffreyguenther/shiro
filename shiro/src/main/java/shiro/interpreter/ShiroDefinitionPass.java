// $ANTLR 3.4 /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g 2012-11-16 16:23:02

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


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class ShiroDefinitionPass extends TreeParser {
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


    public ShiroDefinitionPass(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public ShiroDefinitionPass(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return ShiroDefinitionPass.tokenNames; }
    public String getGrammarFileName() { return "/home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g"; }



    // Declare Graph object for manipulating dependency graph
    private SubjunctiveParametricSystem pSystem;
    private Node nodeToReturn = null;

    public Node getCreatedNode(){
    return nodeToReturn;
    }



    // $ANTLR start "shiro"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:33:1: shiro[SubjunctiveParametricSystem ps] returns [Node node] : statement ;
    public final Node shiro(SubjunctiveParametricSystem ps) throws RecognitionException {
        Node node = null;



        	pSystem = ps; 

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:37:2: ( statement )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:37:5: statement
            {
            pushFollow(FOLLOW_statement_in_shiro50);
            statement();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return node;
    }
    // $ANTLR end "shiro"



    // $ANTLR start "statement"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:40:1: statement : ( nodestmt[null, pSystem] | sNode[null, pSystem] | graphDecl | statestmt | collection | view );
    public final void statement() throws RecognitionException {
        Node nodestmt1 =null;


        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:41:2: ( nodestmt[null, pSystem] | sNode[null, pSystem] | graphDecl | statestmt | collection | view )
            int alt1=6;
            switch ( input.LA(1) ) {
            case NODE_STMT:
                {
                alt1=1;
                }
                break;
            case 56:
                {
                alt1=2;
                }
                break;
            case 51:
                {
                alt1=3;
                }
                break;
            case STATE_DECL:
                {
                alt1=4;
                }
                break;
            case 48:
                {
                alt1=5;
                }
                break;
            case 57:
                {
                alt1=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }

            switch (alt1) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:41:4: nodestmt[null, pSystem]
                    {
                    pushFollow(FOLLOW_nodestmt_in_statement64);
                    nodestmt1=nodestmt(null, pSystem);

                    state._fsp--;


                    nodeToReturn = nodestmt1;

                    }
                    break;
                case 2 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:42:5: sNode[null, pSystem]
                    {
                    pushFollow(FOLLOW_sNode_in_statement74);
                    sNode(null, pSystem);

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:43:4: graphDecl
                    {
                    pushFollow(FOLLOW_graphDecl_in_statement84);
                    graphDecl();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:44:4: statestmt
                    {
                    pushFollow(FOLLOW_statestmt_in_statement89);
                    statestmt();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:45:4: collection
                    {
                    pushFollow(FOLLOW_collection_in_statement94);
                    collection();

                    state._fsp--;


                    }
                    break;
                case 6 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:46:4: view
                    {
                    pushFollow(FOLLOW_view_in_statement99);
                    view();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "statement"



    // $ANTLR start "view"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:49:1: view : ^( 'view' IDENT mfName IDENT ) ;
    public final void view() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:49:6: ( ^( 'view' IDENT mfName IDENT ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:49:8: ^( 'view' IDENT mfName IDENT )
            {
            match(input,57,FOLLOW_57_in_view110); 

            match(input, Token.DOWN, null); 
            match(input,IDENT,FOLLOW_IDENT_in_view112); 

            pushFollow(FOLLOW_mfName_in_view114);
            mfName();

            state._fsp--;


            match(input,IDENT,FOLLOW_IDENT_in_view116); 

            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "view"



    // $ANTLR start "collection"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:52:1: collection : ^( 'collection' IDENT orderingFunc path[null] ( collItem )+ ) ;
    public final void collection() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:53:2: ( ^( 'collection' IDENT orderingFunc path[null] ( collItem )+ ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:53:4: ^( 'collection' IDENT orderingFunc path[null] ( collItem )+ )
            {
            match(input,48,FOLLOW_48_in_collection130); 

            match(input, Token.DOWN, null); 
            match(input,IDENT,FOLLOW_IDENT_in_collection132); 

            pushFollow(FOLLOW_orderingFunc_in_collection134);
            orderingFunc();

            state._fsp--;


            pushFollow(FOLLOW_path_in_collection136);
            path(null);

            state._fsp--;


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:53:49: ( collItem )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==IDENT) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:53:49: collItem
            	    {
            	    pushFollow(FOLLOW_collItem_in_collection139);
            	    collItem();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "collection"



    // $ANTLR start "collItem"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:56:1: collItem : IDENT ;
    public final void collItem() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:56:9: ( IDENT )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:56:11: IDENT
            {
            match(input,IDENT,FOLLOW_IDENT_in_collItem150); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "collItem"



    // $ANTLR start "orderingFunc"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:59:1: orderingFunc : IDENT ;
    public final void orderingFunc() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:60:2: ( IDENT )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:60:4: IDENT
            {
            match(input,IDENT,FOLLOW_IDENT_in_orderingFunc161); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "orderingFunc"



    // $ANTLR start "statestmt"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:63:1: statestmt : ^( STATE_DECL stateHeader ) ;
    public final void statestmt() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:64:2: ( ^( STATE_DECL stateHeader ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:64:4: ^( STATE_DECL stateHeader )
            {
            match(input,STATE_DECL,FOLLOW_STATE_DECL_in_statestmt174); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_stateHeader_in_statestmt176);
            stateHeader();

            state._fsp--;


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "statestmt"



    // $ANTLR start "stateHeader"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:67:1: stateHeader : ( stateTimeStmt | stateCommentStmt | stateParentStmt | stateGraphStmt | activation )+ ;
    public final void stateHeader() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:68:2: ( ( stateTimeStmt | stateCommentStmt | stateParentStmt | stateGraphStmt | activation )+ )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:68:5: ( stateTimeStmt | stateCommentStmt | stateParentStmt | stateGraphStmt | activation )+
            {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:68:5: ( stateTimeStmt | stateCommentStmt | stateParentStmt | stateGraphStmt | activation )+
            int cnt3=0;
            loop3:
            do {
                int alt3=6;
                switch ( input.LA(1) ) {
                case 44:
                    {
                    alt3=1;
                    }
                    break;
                case 41:
                    {
                    alt3=2;
                    }
                    break;
                case 43:
                    {
                    alt3=3;
                    }
                    break;
                case 42:
                    {
                    alt3=4;
                    }
                    break;
                case ACTIVATION:
                    {
                    alt3=5;
                    }
                    break;

                }

                switch (alt3) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:68:6: stateTimeStmt
            	    {
            	    pushFollow(FOLLOW_stateTimeStmt_in_stateHeader191);
            	    stateTimeStmt();

            	    state._fsp--;


            	    }
            	    break;
            	case 2 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:68:22: stateCommentStmt
            	    {
            	    pushFollow(FOLLOW_stateCommentStmt_in_stateHeader195);
            	    stateCommentStmt();

            	    state._fsp--;


            	    }
            	    break;
            	case 3 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:68:41: stateParentStmt
            	    {
            	    pushFollow(FOLLOW_stateParentStmt_in_stateHeader199);
            	    stateParentStmt();

            	    state._fsp--;


            	    }
            	    break;
            	case 4 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:68:58: stateGraphStmt
            	    {
            	    pushFollow(FOLLOW_stateGraphStmt_in_stateHeader202);
            	    stateGraphStmt();

            	    state._fsp--;


            	    }
            	    break;
            	case 5 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:68:75: activation
            	    {
            	    pushFollow(FOLLOW_activation_in_stateHeader206);
            	    activation();

            	    state._fsp--;


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


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "stateHeader"



    // $ANTLR start "stateTimeStmt"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:71:1: stateTimeStmt : ^( 'Time' time ) ;
    public final void stateTimeStmt() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:72:2: ( ^( 'Time' time ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:72:4: ^( 'Time' time )
            {
            match(input,44,FOLLOW_44_in_stateTimeStmt224); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_time_in_stateTimeStmt226);
            time();

            state._fsp--;


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "stateTimeStmt"



    // $ANTLR start "stateCommentStmt"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:75:1: stateCommentStmt : ^( 'Comment' comment ) ;
    public final void stateCommentStmt() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:76:2: ( ^( 'Comment' comment ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:76:4: ^( 'Comment' comment )
            {
            match(input,41,FOLLOW_41_in_stateCommentStmt239); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_comment_in_stateCommentStmt241);
            comment();

            state._fsp--;


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "stateCommentStmt"



    // $ANTLR start "stateParentStmt"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:79:1: stateParentStmt : ^( 'Parent' stateParent ) ;
    public final void stateParentStmt() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:80:2: ( ^( 'Parent' stateParent ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:80:4: ^( 'Parent' stateParent )
            {
            match(input,43,FOLLOW_43_in_stateParentStmt256); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_stateParent_in_stateParentStmt258);
            stateParent();

            state._fsp--;


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "stateParentStmt"



    // $ANTLR start "stateGraphStmt"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:83:1: stateGraphStmt : ^( 'Graph' stateGraph ) ;
    public final void stateGraphStmt() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:84:2: ( ^( 'Graph' stateGraph ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:84:4: ^( 'Graph' stateGraph )
            {
            match(input,42,FOLLOW_42_in_stateGraphStmt272); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_stateGraph_in_stateGraphStmt274);
            stateGraph();

            state._fsp--;


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "stateGraphStmt"



    // $ANTLR start "stateName"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:87:1: stateName : IDENT ;
    public final void stateName() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:88:2: ( IDENT )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:88:4: IDENT
            {
            match(input,IDENT,FOLLOW_IDENT_in_stateName287); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "stateName"



    // $ANTLR start "time"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:91:1: time : STRING_LITERAL ;
    public final void time() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:91:6: ( STRING_LITERAL )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:91:8: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_time298); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "time"



    // $ANTLR start "comment"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:94:1: comment : STRING_LITERAL ;
    public final void comment() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:94:9: ( STRING_LITERAL )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:94:11: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_comment310); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "comment"



    // $ANTLR start "stateParent"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:97:1: stateParent : IDENT ;
    public final void stateParent() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:98:2: ( IDENT )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:98:4: IDENT
            {
            match(input,IDENT,FOLLOW_IDENT_in_stateParent322); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "stateParent"



    // $ANTLR start "stateGraph"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:101:1: stateGraph : IDENT ;
    public final void stateGraph() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:102:2: ( IDENT )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:102:4: IDENT
            {
            match(input,IDENT,FOLLOW_IDENT_in_stateGraph334); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "stateGraph"



    // $ANTLR start "activationPath"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:105:1: activationPath : l= activation ( '.' (r= activation | activationList ) )* ;
    public final void activationPath() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:106:2: (l= activation ( '.' (r= activation | activationList ) )* )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:106:4: l= activation ( '.' (r= activation | activationList ) )*
            {
            pushFollow(FOLLOW_activation_in_activationPath348);
            activation();

            state._fsp--;


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:106:17: ( '.' (r= activation | activationList ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==37) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:106:18: '.' (r= activation | activationList )
            	    {
            	    match(input,37,FOLLOW_37_in_activationPath351); 

            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:106:22: (r= activation | activationList )
            	    int alt4=2;
            	    int LA4_0 = input.LA(1);

            	    if ( (LA4_0==ACTIVATION) ) {
            	        alt4=1;
            	    }
            	    else if ( (LA4_0==ACTIVATION_LIST) ) {
            	        alt4=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 4, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt4) {
            	        case 1 :
            	            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:106:23: r= activation
            	            {
            	            pushFollow(FOLLOW_activation_in_activationPath356);
            	            activation();

            	            state._fsp--;


            	            }
            	            break;
            	        case 2 :
            	            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:106:38: activationList
            	            {
            	            pushFollow(FOLLOW_activationList_in_activationPath360);
            	            activationList();

            	            state._fsp--;


            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "activationPath"



    // $ANTLR start "activationList"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:109:1: activationList : ^( ACTIVATION_LIST ( activation )+ ) ;
    public final void activationList() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:110:2: ( ^( ACTIVATION_LIST ( activation )+ ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:110:4: ^( ACTIVATION_LIST ( activation )+ )
            {
            match(input,ACTIVATION_LIST,FOLLOW_ACTIVATION_LIST_in_activationList375); 

            match(input, Token.DOWN, null); 
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:110:22: ( activation )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==ACTIVATION) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:110:22: activation
            	    {
            	    pushFollow(FOLLOW_activation_in_activationList377);
            	    activation();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "activationList"



    // $ANTLR start "activation"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:113:1: activation : ^( ACTIVATION IDENT ( IDENT )? ) ;
    public final void activation() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:114:2: ( ^( ACTIVATION IDENT ( IDENT )? ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:114:4: ^( ACTIVATION IDENT ( IDENT )? )
            {
            match(input,ACTIVATION,FOLLOW_ACTIVATION_in_activation392); 

            match(input, Token.DOWN, null); 
            match(input,IDENT,FOLLOW_IDENT_in_activation394); 

            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:114:23: ( IDENT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==IDENT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:114:23: IDENT
                    {
                    match(input,IDENT,FOLLOW_IDENT_in_activation396); 

                    }
                    break;

            }


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "activation"



    // $ANTLR start "graphDecl"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:118:1: graphDecl : ^( 'graph' IDENT ( graphLine )+ ) ;
    public final void graphDecl() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:119:2: ( ^( 'graph' IDENT ( graphLine )+ ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:119:4: ^( 'graph' IDENT ( graphLine )+ )
            {
            match(input,51,FOLLOW_51_in_graphDecl411); 

            match(input, Token.DOWN, null); 
            match(input,IDENT,FOLLOW_IDENT_in_graphDecl413); 

            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:119:20: ( graphLine )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==PORT_ASSIGNMENT||LA8_0==36) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:119:20: graphLine
            	    {
            	    pushFollow(FOLLOW_graphLine_in_graphDecl415);
            	    graphLine();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "graphDecl"



    // $ANTLR start "graphLine"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:122:1: graphLine : ( nodeProduction | portAssignment[null] );
    public final void graphLine() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:123:2: ( nodeProduction | portAssignment[null] )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==36) ) {
                alt9=1;
            }
            else if ( (LA9_0==PORT_ASSIGNMENT) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }
            switch (alt9) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:123:4: nodeProduction
                    {
                    pushFollow(FOLLOW_nodeProduction_in_graphLine429);
                    nodeProduction();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:124:5: portAssignment[null]
                    {
                    pushFollow(FOLLOW_portAssignment_in_graphLine436);
                    portAssignment(null);

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "graphLine"



    // $ANTLR start "nodeInternal"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:128:1: nodeInternal[Node n, Scope sc] : ( nodeProduction | portAssignment[null] | portstmt[n] | nodestmt[n, sc] | sNode[n, sc] )+ ;
    public final void nodeInternal(Node n, Scope sc) throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:129:2: ( ( nodeProduction | portAssignment[null] | portstmt[n] | nodestmt[n, sc] | sNode[n, sc] )+ )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:129:4: ( nodeProduction | portAssignment[null] | portstmt[n] | nodestmt[n, sc] | sNode[n, sc] )+
            {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:129:4: ( nodeProduction | portAssignment[null] | portstmt[n] | nodestmt[n, sc] | sNode[n, sc] )+
            int cnt10=0;
            loop10:
            do {
                int alt10=6;
                switch ( input.LA(1) ) {
                case 36:
                    {
                    alt10=1;
                    }
                    break;
                case PORT_ASSIGNMENT:
                    {
                    alt10=2;
                    }
                    break;
                case PORT_DECL:
                case PORT_INIT:
                    {
                    alt10=3;
                    }
                    break;
                case NODE_STMT:
                    {
                    alt10=4;
                    }
                    break;
                case 56:
                    {
                    alt10=5;
                    }
                    break;

                }

                switch (alt10) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:129:5: nodeProduction
            	    {
            	    pushFollow(FOLLOW_nodeProduction_in_nodeInternal451);
            	    nodeProduction();

            	    state._fsp--;


            	    }
            	    break;
            	case 2 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:130:4: portAssignment[null]
            	    {
            	    pushFollow(FOLLOW_portAssignment_in_nodeInternal458);
            	    portAssignment(null);

            	    state._fsp--;


            	    }
            	    break;
            	case 3 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:131:4: portstmt[n]
            	    {
            	    pushFollow(FOLLOW_portstmt_in_nodeInternal466);
            	    portstmt(n);

            	    state._fsp--;


            	    }
            	    break;
            	case 4 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:132:4: nodestmt[n, sc]
            	    {
            	    pushFollow(FOLLOW_nodestmt_in_nodeInternal480);
            	    nodestmt(n, sc);

            	    state._fsp--;


            	    }
            	    break;
            	case 5 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:133:4: sNode[n, sc]
            	    {
            	    pushFollow(FOLLOW_sNode_in_nodeInternal493);
            	    sNode(n, sc);

            	    state._fsp--;


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


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "nodeInternal"



    // $ANTLR start "nodestmt"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:136:1: nodestmt[Node containerNode, Scope sc] returns [Node n] : ^( NODE_STMT IDENT ( activeSelector )? nodeInternal[n, n] ) ;
    public final Node nodestmt(Node containerNode, Scope sc) throws RecognitionException {
        Node n = null;


        CommonTree IDENT2=null;
        ShiroDefinitionPass.activeSelector_return activeSelector3 =null;


        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:137:2: ( ^( NODE_STMT IDENT ( activeSelector )? nodeInternal[n, n] ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:137:4: ^( NODE_STMT IDENT ( activeSelector )? nodeInternal[n, n] )
            {
            match(input,NODE_STMT,FOLLOW_NODE_STMT_in_nodestmt522); 

            match(input, Token.DOWN, null); 
            IDENT2=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_nodestmt524); 



            // if the node being created is contained by another node
            if(containerNode != null){
            	// create a new node
            	n = new Node((IDENT2!=null?IDENT2.getText():null), sc);
            	// add the node as a nested node
            	containerNode.addNestedContainer(n);
            	
            }else{
            	n = new Node((IDENT2!=null?IDENT2.getText():null), sc);
            }


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:151:3: ( activeSelector )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==EVAL_SELECT) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:151:3: activeSelector
                    {
                    pushFollow(FOLLOW_activeSelector_in_nodestmt532);
                    activeSelector3=activeSelector();

                    state._fsp--;


                    }
                    break;

            }


            pushFollow(FOLLOW_nodeInternal_in_nodestmt535);
            nodeInternal(n, n);

            state._fsp--;


            match(input, Token.UP, null); 



            // set the active evalauated port. 
            // This depends on nodeInternal adding ports and nodes before hand.
            Set<Port> evalPorts = n.getEvaluatedPorts();

            // find the active port and activate it in the node
            if(evalPorts.size() > 1) {
            	for(Port p: evalPorts){
            		if(p.getName().equals((activeSelector3!=null?activeSelector3.activeUpdate:null))){
            			try{
            				n.activate(p.getName());
            			}catch (Exception epnf){
            				System.out.println((activeSelector3!=null?activeSelector3.line:0) + ":" + (activeSelector3!=null?activeSelector3.pos:0)
            					+ " " + (activeSelector3!=null?activeSelector3.activeUpdate:null)
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
            		System.out.println((activeSelector3!=null?activeSelector3.line:0) + ":" + (activeSelector3!=null?activeSelector3.pos:0)
            			+ " " + (activeSelector3!=null?activeSelector3.activeUpdate:null)
            			+ " is not defined.");
            		System.err.println(epnf);
            	}
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return n;
    }
    // $ANTLR end "nodestmt"



    // $ANTLR start "sNode"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:185:1: sNode[Node containerNode, Scope sc] returns [SubjunctiveNode n] : ^( 'subjunctive node' IDENT subjunctSelector ( subjunctDeclNodeProd[containerNode, sc] | subjunctDecl[containerNode, sc] )* ) ;
    public final SubjunctiveNode sNode(Node containerNode, Scope sc) throws RecognitionException {
        SubjunctiveNode n = null;


        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:186:2: ( ^( 'subjunctive node' IDENT subjunctSelector ( subjunctDeclNodeProd[containerNode, sc] | subjunctDecl[containerNode, sc] )* ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:186:4: ^( 'subjunctive node' IDENT subjunctSelector ( subjunctDeclNodeProd[containerNode, sc] | subjunctDecl[containerNode, sc] )* )
            {
            match(input,56,FOLLOW_56_in_sNode560); 

            match(input, Token.DOWN, null); 
            match(input,IDENT,FOLLOW_IDENT_in_sNode562); 

            pushFollow(FOLLOW_subjunctSelector_in_sNode564);
            subjunctSelector();

            state._fsp--;


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:186:48: ( subjunctDeclNodeProd[containerNode, sc] | subjunctDecl[containerNode, sc] )*
            loop12:
            do {
                int alt12=3;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==SUBJ_NODE_PROD) ) {
                    alt12=1;
                }
                else if ( (LA12_0==55) ) {
                    alt12=2;
                }


                switch (alt12) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:186:49: subjunctDeclNodeProd[containerNode, sc]
            	    {
            	    pushFollow(FOLLOW_subjunctDeclNodeProd_in_sNode567);
            	    subjunctDeclNodeProd(containerNode, sc);

            	    state._fsp--;


            	    }
            	    break;
            	case 2 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:186:91: subjunctDecl[containerNode, sc]
            	    {
            	    pushFollow(FOLLOW_subjunctDecl_in_sNode572);
            	    subjunctDecl(containerNode, sc);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            match(input, Token.UP, null); 



            // create a subjunctive node
            // set the name and the full path of the node
            // add the enclosed nodes


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return n;
    }
    // $ANTLR end "sNode"



    // $ANTLR start "subjunctDeclNodeProd"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:194:1: subjunctDeclNodeProd[Node containerNode, Scope sc] : ^( SUBJ_NODE_PROD parent= IDENT child= IDENT nodeInternal[containerNode, sc] ) ;
    public final void subjunctDeclNodeProd(Node containerNode, Scope sc) throws RecognitionException {
        CommonTree parent=null;
        CommonTree child=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:195:2: ( ^( SUBJ_NODE_PROD parent= IDENT child= IDENT nodeInternal[containerNode, sc] ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:195:4: ^( SUBJ_NODE_PROD parent= IDENT child= IDENT nodeInternal[containerNode, sc] )
            {
            match(input,SUBJ_NODE_PROD,FOLLOW_SUBJ_NODE_PROD_in_subjunctDeclNodeProd593); 

            match(input, Token.DOWN, null); 
            parent=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_subjunctDeclNodeProd597); 

            child=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_subjunctDeclNodeProd601); 

            pushFollow(FOLLOW_nodeInternal_in_subjunctDeclNodeProd603);
            nodeInternal(containerNode, sc);

            state._fsp--;


            match(input, Token.UP, null); 



            // duplicate the node given
            // add the children nodes.


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "subjunctDeclNodeProd"



    // $ANTLR start "subjunctDecl"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:202:1: subjunctDecl[Node containerNode, Scope sc] : ^( 'subjunct' name= IDENT ( activeSelector )? nodeInternal[containerNode, sc] ) ;
    public final void subjunctDecl(Node containerNode, Scope sc) throws RecognitionException {
        CommonTree name=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:203:2: ( ^( 'subjunct' name= IDENT ( activeSelector )? nodeInternal[containerNode, sc] ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:203:4: ^( 'subjunct' name= IDENT ( activeSelector )? nodeInternal[containerNode, sc] )
            {
            match(input,55,FOLLOW_55_in_subjunctDecl623); 

            match(input, Token.DOWN, null); 
            name=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_subjunctDecl627); 

            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:203:28: ( activeSelector )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==EVAL_SELECT) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:203:28: activeSelector
                    {
                    pushFollow(FOLLOW_activeSelector_in_subjunctDecl629);
                    activeSelector();

                    state._fsp--;


                    }
                    break;

            }


            pushFollow(FOLLOW_nodeInternal_in_subjunctDecl632);
            nodeInternal(containerNode, sc);

            state._fsp--;


            match(input, Token.UP, null); 



            // create a node with the given name
            // set the node's full path
            // if an active update method is given, use it.
            // add the children nodes


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "subjunctDecl"



    // $ANTLR start "subjunctSelector"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:212:1: subjunctSelector returns [String selectedName] : ^( SUBJ_SELECT IDENT ) ;
    public final String subjunctSelector() throws RecognitionException {
        String selectedName = null;


        CommonTree IDENT4=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:213:2: ( ^( SUBJ_SELECT IDENT ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:213:4: ^( SUBJ_SELECT IDENT )
            {
            match(input,SUBJ_SELECT,FOLLOW_SUBJ_SELECT_in_subjunctSelector652); 

            match(input, Token.DOWN, null); 
            IDENT4=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_subjunctSelector654); 

            selectedName = (IDENT4!=null?IDENT4.getText():null);

            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return selectedName;
    }
    // $ANTLR end "subjunctSelector"


    public static class activeSelector_return extends TreeRuleReturnScope {
        public String activeUpdate;
        public int line;
        public int pos;
    };


    // $ANTLR start "activeSelector"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:216:1: activeSelector returns [String activeUpdate, int line, int pos] : ^( EVAL_SELECT i= IDENT ) ;
    public final ShiroDefinitionPass.activeSelector_return activeSelector() throws RecognitionException {
        ShiroDefinitionPass.activeSelector_return retval = new ShiroDefinitionPass.activeSelector_return();
        retval.start = input.LT(1);


        CommonTree i=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:217:2: ( ^( EVAL_SELECT i= IDENT ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:217:4: ^( EVAL_SELECT i= IDENT )
            {
            match(input,EVAL_SELECT,FOLLOW_EVAL_SELECT_in_activeSelector674); 

            match(input, Token.DOWN, null); 
            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_activeSelector678); 

            match(input, Token.UP, null); 


            retval.line = (i!=null?i.getLine():0); retval.pos = (i!=null?i.getCharPositionInLine():0);


            // add field for getting the name of the port
            retval.activeUpdate = (i!=null?i.getText():null);


            }

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



    // $ANTLR start "nodeProduction"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:224:1: nodeProduction : ( ^( '->' path[null] activationPath ) | ^( '->' l= nodeProduction activationPath ) );
    public final void nodeProduction() throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:225:2: ( ^( '->' path[null] activationPath ) | ^( '->' l= nodeProduction activationPath ) )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==36) ) {
                int LA14_1 = input.LA(2);

                if ( (LA14_1==DOWN) ) {
                    int LA14_2 = input.LA(3);

                    if ( (LA14_2==PATH) ) {
                        alt14=1;
                    }
                    else if ( (LA14_2==36) ) {
                        alt14=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 14, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }
            switch (alt14) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:225:5: ^( '->' path[null] activationPath )
                    {
                    match(input,36,FOLLOW_36_in_nodeProduction697); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_path_in_nodeProduction699);
                    path(null);

                    state._fsp--;


                    pushFollow(FOLLOW_activationPath_in_nodeProduction703);
                    activationPath();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    }
                    break;
                case 2 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:226:8: ^( '->' l= nodeProduction activationPath )
                    {
                    match(input,36,FOLLOW_36_in_nodeProduction714); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_nodeProduction_in_nodeProduction718);
                    nodeProduction();

                    state._fsp--;


                    pushFollow(FOLLOW_activationPath_in_nodeProduction720);
                    activationPath();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "nodeProduction"



    // $ANTLR start "portAssignment"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:229:1: portAssignment[Node n] : ^( PORT_ASSIGNMENT path[n] mfparams[n] ) ;
    public final void portAssignment(Node n) throws RecognitionException {
        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:230:2: ( ^( PORT_ASSIGNMENT path[n] mfparams[n] ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:230:4: ^( PORT_ASSIGNMENT path[n] mfparams[n] )
            {
            match(input,PORT_ASSIGNMENT,FOLLOW_PORT_ASSIGNMENT_in_portAssignment735); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_path_in_portAssignment737);
            path(n);

            state._fsp--;


            pushFollow(FOLLOW_mfparams_in_portAssignment740);
            mfparams(n);

            state._fsp--;


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "portAssignment"



    // $ANTLR start "portDecl"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:234:1: portDecl[Node n] returns [Port p] : ^( PORT_DECL ^( PORT_TAG portType ) portName mfName ) ;
    public final Port portDecl(Node n) throws RecognitionException {
        Port p = null;


        ShiroDefinitionPass.mfName_return mfName5 =null;

        String portName6 =null;

        String portType7 =null;


        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:235:2: ( ^( PORT_DECL ^( PORT_TAG portType ) portName mfName ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:235:4: ^( PORT_DECL ^( PORT_TAG portType ) portName mfName )
            {
            match(input,PORT_DECL,FOLLOW_PORT_DECL_in_portDecl762); 

            match(input, Token.DOWN, null); 
            match(input,PORT_TAG,FOLLOW_PORT_TAG_in_portDecl765); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_portType_in_portDecl767);
            portType7=portType();

            state._fsp--;


            match(input, Token.UP, null); 


            pushFollow(FOLLOW_portName_in_portDecl770);
            portName6=portName();

            state._fsp--;


            pushFollow(FOLLOW_mfName_in_portDecl772);
            mfName5=mfName();

            state._fsp--;


            match(input, Token.UP, null); 



            // create a new port
            MultiFunction mf = pSystem.getFunction((mfName5!=null?mfName5.name:null));

            //. detect if the multifunction exists
            if(mf != null){
            	// create the port
            	String path = n.getFullName() + "." + portName6;
            	// create the port
            	p = new Port(path, mf);
            }else{
            	System.out.println((mfName5!=null?mfName5.line:0) + ":" + (mfName5!=null?mfName5.pos:0) + " Unknown multifunction: " + (mfName5!=null?mfName5.name:null));
            }

            if(portType7.equals("eval")){
            	p.setPortType(PortType.Evaluated);
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "portDecl"



    // $ANTLR start "portDeclInit"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:256:1: portDeclInit[Node n] returns [Port p] : ^( PORT_INIT ^( PORT_TAG portType ) portName mfCall[n] ) ;
    public final Port portDeclInit(Node n) throws RecognitionException {
        Port p = null;


        ShiroDefinitionPass.mfCall_return mfCall8 =null;

        String portName9 =null;

        String portType10 =null;


        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:257:2: ( ^( PORT_INIT ^( PORT_TAG portType ) portName mfCall[n] ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:257:4: ^( PORT_INIT ^( PORT_TAG portType ) portName mfCall[n] )
            {
            match(input,PORT_INIT,FOLLOW_PORT_INIT_in_portDeclInit794); 

            match(input, Token.DOWN, null); 
            match(input,PORT_TAG,FOLLOW_PORT_TAG_in_portDeclInit797); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_portType_in_portDeclInit799);
            portType10=portType();

            state._fsp--;


            match(input, Token.UP, null); 


            pushFollow(FOLLOW_portName_in_portDeclInit802);
            portName9=portName();

            state._fsp--;


            pushFollow(FOLLOW_mfCall_in_portDeclInit804);
            mfCall8=mfCall(n);

            state._fsp--;


            match(input, Token.UP, null); 



            // create a new port
            MultiFunction mf = pSystem.getFunction((mfCall8!=null?mfCall8.name:null));

            // detect if the multifunction exists
            if(mf != null){
            	// create a path based on the node's name
            	String path = n.getFullName() + "." + portName9;
            	// create the port
            	p = new Port(path, (mfCall8!=null?mfCall8.expressions:null), mf);
            }else{
            	System.out.println((mfCall8!=null?mfCall8.line:0) + ":" + (mfCall8!=null?mfCall8.pos:0) + " Unknown multifunction: " + (mfCall8!=null?mfCall8.name:null));
            }

            if(portType10.equals("eval")){
            	p.setPortType(PortType.Evaluated);
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "portDeclInit"



    // $ANTLR start "portstmt"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:280:1: portstmt[Node n] : ( portDecl[n] | portDeclInit[n] ) ;
    public final void portstmt(Node n) throws RecognitionException {
        Port portDecl11 =null;

        Port portDeclInit12 =null;


        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:281:2: ( ( portDecl[n] | portDeclInit[n] ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:281:4: ( portDecl[n] | portDeclInit[n] )
            {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:281:4: ( portDecl[n] | portDeclInit[n] )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==PORT_DECL) ) {
                alt15=1;
            }
            else if ( (LA15_0==PORT_INIT) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }
            switch (alt15) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:281:5: portDecl[n]
                    {
                    pushFollow(FOLLOW_portDecl_in_portstmt824);
                    portDecl11=portDecl(n);

                    state._fsp--;



                    n.addPort(portDecl11);


                    }
                    break;
                case 2 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:285:5: portDeclInit[n]
                    {
                    pushFollow(FOLLOW_portDeclInit_in_portstmt834);
                    portDeclInit12=portDeclInit(n);

                    state._fsp--;




                    if(portDeclInit12.getPortType() == PortType.Evaluated){
                    	n.addEvaluatedPort(portDeclInit12);
                    }else{
                    	n.addPort(portDeclInit12);
                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "portstmt"



    // $ANTLR start "portName"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:297:1: portName returns [String name] : i= IDENT ;
    public final String portName() throws RecognitionException {
        String name = null;


        CommonTree i=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:298:2: (i= IDENT )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:298:4: i= IDENT
            {
            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_portName859); 

            name = (i!=null?i.getText():null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return name;
    }
    // $ANTLR end "portName"



    // $ANTLR start "portType"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:301:1: portType returns [String portType] : (s= 'port' |s= 'eval' );
    public final String portType() throws RecognitionException {
        String portType = null;


        CommonTree s=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:302:2: (s= 'port' |s= 'eval' )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==53) ) {
                alt16=1;
            }
            else if ( (LA16_0==50) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:302:4: s= 'port'
                    {
                    s=(CommonTree)match(input,53,FOLLOW_53_in_portType879); 

                    portType = (s!=null?s.getText():null);

                    }
                    break;
                case 2 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:302:38: s= 'eval'
                    {
                    s=(CommonTree)match(input,50,FOLLOW_50_in_portType887); 

                    portType = (s!=null?s.getText():null);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return portType;
    }
    // $ANTLR end "portType"


    public static class mfCall_return extends TreeRuleReturnScope {
        public String name;
        public int line;
        public int pos;
        public List<Expression> expressions;
    };


    // $ANTLR start "mfCall"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:306:1: mfCall[Scope sc] returns [String name, int line, int pos, List<Expression> expressions] : ^( mfName mfparams[sc] ) ;
    public final ShiroDefinitionPass.mfCall_return mfCall(Scope sc) throws RecognitionException {
        ShiroDefinitionPass.mfCall_return retval = new ShiroDefinitionPass.mfCall_return();
        retval.start = input.LT(1);


        ShiroDefinitionPass.mfName_return mfName13 =null;

        List<Expression> mfparams14 =null;


        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:307:2: ( ^( mfName mfparams[sc] ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:307:4: ^( mfName mfparams[sc] )
            {
            pushFollow(FOLLOW_mfName_in_mfCall909);
            mfName13=mfName();

            state._fsp--;


            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_mfparams_in_mfCall911);
            mfparams14=mfparams(sc);

            state._fsp--;


            match(input, Token.UP, null); 


            retval.name = (mfName13!=null?mfName13.name:null);
            retval.line = (mfName13!=null?mfName13.line:0);
            retval.pos = (mfName13!=null?mfName13.pos:0);
            retval.expressions = mfparams14;


            }

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
    };


    // $ANTLR start "mfName"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:315:1: mfName returns [String name, int line, int pos] : i= IDENT ;
    public final ShiroDefinitionPass.mfName_return mfName() throws RecognitionException {
        ShiroDefinitionPass.mfName_return retval = new ShiroDefinitionPass.mfName_return();
        retval.start = input.LT(1);


        CommonTree i=null;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:316:2: (i= IDENT )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:316:4: i= IDENT
            {
            i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_mfName935); 

            retval.name = (i!=null?i.getText():null); retval.line = (i!=null?i.getLine():0); retval.pos = (i!=null?i.getCharPositionInLine():0);

            }

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



    // $ANTLR start "mfparams"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:319:1: mfparams[Scope sc] returns [List<Expression> expressions] : (exps= expression[sc] )+ ;
    public final List<Expression> mfparams(Scope sc) throws RecognitionException {
        List<Expression> expressions = null;


        Expression exps =null;



        expressions = new ArrayList<Expression>();

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:323:2: ( (exps= expression[sc] )+ )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:323:4: (exps= expression[sc] )+
            {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:323:4: (exps= expression[sc] )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0 >= NUMBER && LA17_0 <= PATH)||LA17_0==29||(LA17_0 >= 32 && LA17_0 <= 33)||LA17_0==35||LA17_0==38||LA17_0==58) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:323:5: exps= expression[sc]
            	    {
            	    pushFollow(FOLLOW_expression_in_mfparams961);
            	    exps=expression(sc);

            	    state._fsp--;


            	    expressions.add(exps);

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

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return expressions;
    }
    // $ANTLR end "mfparams"



    // $ANTLR start "path"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:327:1: path[Scope sc] returns [Path p] : ^( PATH (id= IDENT )+ ( pathIndex )? ) ;
    public final Path path(Scope sc) throws RecognitionException {
        Path p = null;


        CommonTree id=null;
        ShiroDefinitionPass.pathIndex_return pathIndex15 =null;



        List<String> parts = new ArrayList<String>();
        boolean hasPathIndex = false;

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:332:2: ( ^( PATH (id= IDENT )+ ( pathIndex )? ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:332:4: ^( PATH (id= IDENT )+ ( pathIndex )? )
            {
            match(input,PATH,FOLLOW_PATH_in_path990); 

            match(input, Token.DOWN, null); 
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:332:11: (id= IDENT )+
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
            	    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:332:12: id= IDENT
            	    {
            	    id=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_path995); 

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


            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:332:45: ( pathIndex )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==PORT_INDEX) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:332:46: pathIndex
                    {
                    pushFollow(FOLLOW_pathIndex_in_path1001);
                    pathIndex15=pathIndex();

                    state._fsp--;


                     hasPathIndex = true;

                    }
                    break;

            }


            match(input, Token.UP, null); 



            if(hasPathIndex){
            	if ((pathIndex15!=null?pathIndex15.index:0) >=0 ){
            		p = new Path(sc, parts, (pathIndex15!=null?pathIndex15.index:0));
            	}else if((pathIndex15!=null?pathIndex15.key:null) != ""){
            		p = new Path(sc, parts, (pathIndex15!=null?pathIndex15.key:null));
            	}
            }else{
            	p = new Path(sc, parts);
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "path"


    public static class pathIndex_return extends TreeRuleReturnScope {
        public String key;
        public int index;
    };


    // $ANTLR start "pathIndex"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:346:1: pathIndex returns [String key, int index] : ^( PORT_INDEX portIndex ) ;
    public final ShiroDefinitionPass.pathIndex_return pathIndex() throws RecognitionException {
        ShiroDefinitionPass.pathIndex_return retval = new ShiroDefinitionPass.pathIndex_return();
        retval.start = input.LT(1);


        ShiroDefinitionPass.portIndex_return portIndex16 =null;


        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:347:2: ( ^( PORT_INDEX portIndex ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:347:4: ^( PORT_INDEX portIndex )
            {
            match(input,PORT_INDEX,FOLLOW_PORT_INDEX_in_pathIndex1025); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_portIndex_in_pathIndex1027);
            portIndex16=portIndex();

            state._fsp--;


            match(input, Token.UP, null); 



            retval.key = (portIndex16!=null?portIndex16.key:null);
            retval.index = (portIndex16!=null?portIndex16.index:0);


            }

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
        public String key;
        public int index;
    };


    // $ANTLR start "portIndex"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:354:1: portIndex returns [String key, int index] : ( NUMBER | STRING_LITERAL ) ;
    public final ShiroDefinitionPass.portIndex_return portIndex() throws RecognitionException {
        ShiroDefinitionPass.portIndex_return retval = new ShiroDefinitionPass.portIndex_return();
        retval.start = input.LT(1);


        CommonTree NUMBER17=null;
        CommonTree STRING_LITERAL18=null;


        retval.index = -1;
        retval.key = "";

        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:359:2: ( ( NUMBER | STRING_LITERAL ) )
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:359:4: ( NUMBER | STRING_LITERAL )
            {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:359:4: ( NUMBER | STRING_LITERAL )
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
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:359:6: NUMBER
                    {
                    NUMBER17=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_portIndex1052); 

                    retval.index = Integer.parseInt((NUMBER17!=null?NUMBER17.getText():null));

                    }
                    break;
                case 2 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:360:4: STRING_LITERAL
                    {
                    STRING_LITERAL18=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_portIndex1059); 

                    retval.key = (STRING_LITERAL18!=null?STRING_LITERAL18.getText():null).replace("\"","");

                    }
                    break;

            }


            }

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



    // $ANTLR start "expression"
    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:365:1: expression[Scope sc] returns [Expression exp] : ( ^( '+' op1= expression[sc] op2= expression[sc] ) | ^( '-' op1= expression[sc] op2= expression[sc] ) | ^( '*' op1= expression[sc] op2= expression[sc] ) | ^( '/' op1= expression[sc] op2= expression[sc] ) | ^( '%' op1= expression[sc] op2= expression[sc] ) | ^( '|' op1= expression[sc] op2= expression[sc] ) | NUMBER | path[sc] );
    public final Expression expression(Scope sc) throws RecognitionException {
        Expression exp = null;


        CommonTree NUMBER19=null;
        Expression op1 =null;

        Expression op2 =null;

        Path path20 =null;


        try {
            // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:366:2: ( ^( '+' op1= expression[sc] op2= expression[sc] ) | ^( '-' op1= expression[sc] op2= expression[sc] ) | ^( '*' op1= expression[sc] op2= expression[sc] ) | ^( '/' op1= expression[sc] op2= expression[sc] ) | ^( '%' op1= expression[sc] op2= expression[sc] ) | ^( '|' op1= expression[sc] op2= expression[sc] ) | NUMBER | path[sc] )
            int alt21=8;
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
            case PATH:
                {
                alt21=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }

            switch (alt21) {
                case 1 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:366:4: ^( '+' op1= expression[sc] op2= expression[sc] )
                    {
                    match(input,33,FOLLOW_33_in_expression1085); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1089);
                    op1=expression(sc);

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1094);
                    op2=expression(sc);

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new Add(op1, op2);

                    }
                    break;
                case 2 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:367:4: ^( '-' op1= expression[sc] op2= expression[sc] )
                    {
                    match(input,35,FOLLOW_35_in_expression1104); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1108);
                    op1=expression(sc);

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1113);
                    op2=expression(sc);

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new Subtract(op1, op2);

                    }
                    break;
                case 3 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:368:4: ^( '*' op1= expression[sc] op2= expression[sc] )
                    {
                    match(input,32,FOLLOW_32_in_expression1123); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1127);
                    op1=expression(sc);

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1132);
                    op2=expression(sc);

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new Multiply(op1, op2);

                    }
                    break;
                case 4 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:369:4: ^( '/' op1= expression[sc] op2= expression[sc] )
                    {
                    match(input,38,FOLLOW_38_in_expression1142); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1146);
                    op1=expression(sc);

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1151);
                    op2=expression(sc);

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new Divide(op1, op2);

                    }
                    break;
                case 5 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:370:4: ^( '%' op1= expression[sc] op2= expression[sc] )
                    {
                    match(input,29,FOLLOW_29_in_expression1161); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1165);
                    op1=expression(sc);

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1170);
                    op2=expression(sc);

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new Mod(op1, op2);

                    }
                    break;
                case 6 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:371:4: ^( '|' op1= expression[sc] op2= expression[sc] )
                    {
                    match(input,58,FOLLOW_58_in_expression1180); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1184);
                    op1=expression(sc);

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1189);
                    op2=expression(sc);

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new Or(op1, op2);

                    }
                    break;
                case 7 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:372:4: NUMBER
                    {
                    NUMBER19=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_expression1198); 

                    exp = new shiro.expressions.Number(Float.parseFloat((NUMBER19!=null?NUMBER19.getText():null)));

                    }
                    break;
                case 8 :
                    // /home/aga53/Workspace/Eclipse/Shiro/src/shiro/interpreter/ShiroDefinitionPass.g:373:5: path[sc]
                    {
                    pushFollow(FOLLOW_path_in_expression1206);
                    path20=path(sc);

                    state._fsp--;


                    exp = path20;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exp;
    }
    // $ANTLR end "expression"

    // Delegated rules


 

    public static final BitSet FOLLOW_statement_in_shiro50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nodestmt_in_statement64 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sNode_in_statement74 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_graphDecl_in_statement84 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statestmt_in_statement89 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_collection_in_statement94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_view_in_statement99 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_view110 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_view112 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_mfName_in_view114 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_view116 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_48_in_collection130 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_collection132 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_orderingFunc_in_collection134 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_path_in_collection136 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_collItem_in_collection139 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_IDENT_in_collItem150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_orderingFunc161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATE_DECL_in_statestmt174 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stateHeader_in_statestmt176 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_stateTimeStmt_in_stateHeader191 = new BitSet(new long[]{0x00001E0000000012L});
    public static final BitSet FOLLOW_stateCommentStmt_in_stateHeader195 = new BitSet(new long[]{0x00001E0000000012L});
    public static final BitSet FOLLOW_stateParentStmt_in_stateHeader199 = new BitSet(new long[]{0x00001E0000000012L});
    public static final BitSet FOLLOW_stateGraphStmt_in_stateHeader202 = new BitSet(new long[]{0x00001E0000000012L});
    public static final BitSet FOLLOW_activation_in_stateHeader206 = new BitSet(new long[]{0x00001E0000000012L});
    public static final BitSet FOLLOW_44_in_stateTimeStmt224 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_time_in_stateTimeStmt226 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_41_in_stateCommentStmt239 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_comment_in_stateCommentStmt241 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_43_in_stateParentStmt256 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stateParent_in_stateParentStmt258 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_42_in_stateGraphStmt272 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stateGraph_in_stateGraphStmt274 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IDENT_in_stateName287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_time298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_comment310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_stateParent322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_stateGraph334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_activation_in_activationPath348 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_37_in_activationPath351 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_activation_in_activationPath356 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_activationList_in_activationPath360 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_ACTIVATION_LIST_in_activationList375 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_activation_in_activationList377 = new BitSet(new long[]{0x0000000000000018L});
    public static final BitSet FOLLOW_ACTIVATION_in_activation392 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_activation394 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_IDENT_in_activation396 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_51_in_graphDecl411 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_graphDecl413 = new BitSet(new long[]{0x0000001000020000L});
    public static final BitSet FOLLOW_graphLine_in_graphDecl415 = new BitSet(new long[]{0x0000001000020008L});
    public static final BitSet FOLLOW_nodeProduction_in_graphLine429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portAssignment_in_graphLine436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nodeProduction_in_nodeInternal451 = new BitSet(new long[]{0x0100001000162002L});
    public static final BitSet FOLLOW_portAssignment_in_nodeInternal458 = new BitSet(new long[]{0x0100001000162002L});
    public static final BitSet FOLLOW_portstmt_in_nodeInternal466 = new BitSet(new long[]{0x0100001000162002L});
    public static final BitSet FOLLOW_nodestmt_in_nodeInternal480 = new BitSet(new long[]{0x0100001000162002L});
    public static final BitSet FOLLOW_sNode_in_nodeInternal493 = new BitSet(new long[]{0x0100001000162002L});
    public static final BitSet FOLLOW_NODE_STMT_in_nodestmt522 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_nodestmt524 = new BitSet(new long[]{0x0100001000162100L});
    public static final BitSet FOLLOW_activeSelector_in_nodestmt532 = new BitSet(new long[]{0x0100001000162000L});
    public static final BitSet FOLLOW_nodeInternal_in_nodestmt535 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_56_in_sNode560 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_sNode562 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_subjunctSelector_in_sNode564 = new BitSet(new long[]{0x0080000002000008L});
    public static final BitSet FOLLOW_subjunctDeclNodeProd_in_sNode567 = new BitSet(new long[]{0x0080000002000008L});
    public static final BitSet FOLLOW_subjunctDecl_in_sNode572 = new BitSet(new long[]{0x0080000002000008L});
    public static final BitSet FOLLOW_SUBJ_NODE_PROD_in_subjunctDeclNodeProd593 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_subjunctDeclNodeProd597 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENT_in_subjunctDeclNodeProd601 = new BitSet(new long[]{0x0100001000162000L});
    public static final BitSet FOLLOW_nodeInternal_in_subjunctDeclNodeProd603 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_55_in_subjunctDecl623 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_subjunctDecl627 = new BitSet(new long[]{0x0100001000162100L});
    public static final BitSet FOLLOW_activeSelector_in_subjunctDecl629 = new BitSet(new long[]{0x0100001000162000L});
    public static final BitSet FOLLOW_nodeInternal_in_subjunctDecl632 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SUBJ_SELECT_in_subjunctSelector652 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_subjunctSelector654 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EVAL_SELECT_in_activeSelector674 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_activeSelector678 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_36_in_nodeProduction697 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_path_in_nodeProduction699 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_activationPath_in_nodeProduction703 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_36_in_nodeProduction714 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_nodeProduction_in_nodeProduction718 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_activationPath_in_nodeProduction720 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PORT_ASSIGNMENT_in_portAssignment735 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_path_in_portAssignment737 = new BitSet(new long[]{0x0400004B20018000L});
    public static final BitSet FOLLOW_mfparams_in_portAssignment740 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PORT_DECL_in_portDecl762 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_PORT_TAG_in_portDecl765 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_portType_in_portDecl767 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_portName_in_portDecl770 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_mfName_in_portDecl772 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PORT_INIT_in_portDeclInit794 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_PORT_TAG_in_portDeclInit797 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_portType_in_portDeclInit799 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_portName_in_portDeclInit802 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_mfCall_in_portDeclInit804 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_portDecl_in_portstmt824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portDeclInit_in_portstmt834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_portName859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_portType879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_portType887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mfName_in_mfCall909 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_mfparams_in_mfCall911 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IDENT_in_mfName935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_mfparams961 = new BitSet(new long[]{0x0400004B20018002L});
    public static final BitSet FOLLOW_PATH_in_path990 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_path995 = new BitSet(new long[]{0x0000000000080208L});
    public static final BitSet FOLLOW_pathIndex_in_path1001 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PORT_INDEX_in_pathIndex1025 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_portIndex_in_pathIndex1027 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NUMBER_in_portIndex1052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_portIndex1059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_expression1085 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1089 = new BitSet(new long[]{0x0400004B20018000L});
    public static final BitSet FOLLOW_expression_in_expression1094 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_35_in_expression1104 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1108 = new BitSet(new long[]{0x0400004B20018000L});
    public static final BitSet FOLLOW_expression_in_expression1113 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_32_in_expression1123 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1127 = new BitSet(new long[]{0x0400004B20018000L});
    public static final BitSet FOLLOW_expression_in_expression1132 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_38_in_expression1142 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1146 = new BitSet(new long[]{0x0400004B20018000L});
    public static final BitSet FOLLOW_expression_in_expression1151 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_29_in_expression1161 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1165 = new BitSet(new long[]{0x0400004B20018000L});
    public static final BitSet FOLLOW_expression_in_expression1170 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_58_in_expression1180 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1184 = new BitSet(new long[]{0x0400004B20018000L});
    public static final BitSet FOLLOW_expression_in_expression1189 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NUMBER_in_expression1198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_path_in_expression1206 = new BitSet(new long[]{0x0000000000000002L});

}