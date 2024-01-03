/**
 * @author Edoardo Luppi
 */
lexer grammar MTProtoTLLexer;

// lc-ident ::= lc-letter { ident-char }
// ident-char ::= letter | digit | underscore
LcIdent:          [a-z][a-zA-Z0-9_]*;

// uc-ident ::= uc-letter { ident-char }
// ident-char ::= letter | digit | underscore
UcIdent:          [A-Z][a-zA-Z0-9_]*;

// Example: #3fedd339
// The official grammar uses the {1,8} quantifier, but ANTLR does not support it
HexConst:         '#' [0-9a-f]+;

// nat-const ::= digit { digit }
NatConst:         [0-9]+;

// Valid delimiters
Types:            '---types---';
Functions:        '---functions---';

// Keywords
New:              'New';
Final:            'Final';
Empty:            'Empty';

// Single char tokens
Plus:             '+';
OpenPar:          '(';
ClosePar:         ')';
OpenBracket:      '[';
CloseBracket:     ']';
OpenBrace:        '{';
CloseBrace:       '}';
Comma:            ',';
Semicolon:        ';';
Colon:            ':';
Asterisk:         '*';
Hash:             '#';
Dot:              '.';
Underscore:       '_';
QuestMark:        '?';
ExclMark:         '!';
Percent:          '%';
LAngle:           '<';
RAngle:           '>';
Equals:           '=';

// We do not care about comments, delimiters, and whitespaces
Comment:          ('//' .+? '\n'? | '/*' .+? '*/')  -> skip;
Delimiter:        '===' [0-9]+ '==='                -> skip;
WS:               [ \r\n\t]+                        -> skip;
