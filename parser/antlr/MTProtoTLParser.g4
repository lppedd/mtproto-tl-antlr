/**
 * @author Edoardo Luppi
 */
parser grammar MTProtoTLParser;

options {
  tokenVocab = MTProtoTLLexer;
}

tlProgram
    : constrDeclarations (Functions funDeclarations | Types constrDeclarations)* EOF
    ;

funDeclarations
    : declaration*
    ;

constrDeclarations
    : declaration*
    ;

declaration
    : combinatorDecl
    | builtinCombinatorDecl
    | partialAppDecl
    | finalDecl
    ;

typeExpr
    : expr
    ;

// This is in the formal grammar, but it is actually not used anywhere
//
// natExpr
//     : expr
//     ;

expr
    : subexpr*
    ;

subexpr
    : term
    | NatConst Plus subexpr
    | subexpr Plus NatConst
    ;

term
    : OpenPar expr ClosePar
    | typeIdent
    | varIdent
    | NatConst
    | Percent term
    | typeIdent LAngle expr (Comma expr)* RAngle
    ;

typeIdent
    : boxedTypeIdent
    | lcIdentNs
    | Hash
    ;

boxedTypeIdent
    : ucIdentNs
    ;

varIdent
    : LcIdent
    | UcIdent
    ;

typeTerm
    : term
    ;

natTerm
    : term
    ;

//
// Combinator declarations
//

combinatorDecl
    : fullCombinatorId optArgs* args* Equals resultType Semicolon
    ;

fullCombinatorId
    : lcIdentFull
    | Underscore
    ;

combinatorId
    : lcIdentNs
    | Underscore
    ;

optArgs
    : OpenBrace varIdent varIdent* Colon ExclMark? typeExpr CloseBrace
    ;

args
    : simpleArgs
    | bracketArgs
    | parArgs
    | typeArgs
    ;

simpleArgs
    : varIdentOpt Colon conditionalDef? ExclMark? typeTerm
    ;

bracketArgs
    : (varIdentOpt Colon)? (multiplicity Asterisk)? OpenBracket args* CloseBracket
    ;

parArgs
    : OpenPar varIdentOpt varIdentOpt* Colon ExclMark? typeTerm ClosePar
    ;

typeArgs
    : ExclMark? typeTerm
    ;

multiplicity
    : natTerm
    ;

varIdentOpt
    : varIdent
    | Underscore
    ;

conditionalDef
    : varIdent (Dot NatConst)? QuestMark
    ;

resultType
    : boxedTypeIdent subexpr*
    | boxedTypeIdent LAngle subexpr (Comma subexpr)* RAngle
    ;

builtinCombinatorDecl
    : fullCombinatorId QuestMark Equals boxedTypeIdent Semicolon
    ;

//
// Partial applications
//

partialAppDecl
    : partialTypeAppDecl
    | partialCombAppDecl
    ;

partialTypeAppDecl
    : boxedTypeIdent subexpr subexpr* Semicolon
    | boxedTypeIdent LAngle expr (Comma expr)* RAngle Semicolon
    ;

partialCombAppDecl
    : combinatorId subexpr subexpr* Semicolon
    ;

//
// Type finalization
//

finalDecl
    : New boxedTypeIdent Semicolon
    | Final boxedTypeIdent Semicolon
    | Empty boxedTypeIdent Semicolon
    ;

//
// Simple identifiers
//

// namespace-ident ::= lc-ident
namespaceIdent
    : LcIdent
    ;

// lc-ident-ns ::= [ namespace-ident . ] lc-ident
lcIdentNs
    : (namespaceIdent Dot)? LcIdent
    ;

// uc-ident-ns ::= [ namespace-ident . ] uc-ident
ucIdentNs
    : (namespaceIdent Dot)? UcIdent
    ;

// lc-ident-full ::= lc-ident-ns [ # hex-digit *8 ]
lcIdentFull
    : lcIdentNs HexConst?
    ;
