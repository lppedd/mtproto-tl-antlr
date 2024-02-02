package com.github.lppedd.mtproto.tl.parser

import com.github.lppedd.mtproto.tl.parser.ext.assertEof
import com.github.lppedd.mtproto.tl.parser.ext.assertNextToken
import com.github.lppedd.mtproto.tl.parser.generated.MTProtoTLLexer.Tokens
import kotlin.test.Test

/**
 * @author Edoardo Luppi
 */
class MTProtoTLLexerTest : MTProtoTLBaseTest() {
  @Test
  fun lexStringTypeCombinator() {
    val lexer = lexerForText("string ? = String;")
    lexer.assertNextToken(Tokens.LcIdent)
    lexer.assertNextToken(Tokens.QuestMark)
    lexer.assertNextToken(Tokens.Equals)
    lexer.assertNextToken(Tokens.UcIdent)
    lexer.assertNextToken(Tokens.Semicolon)
    lexer.assertEof()
  }

  @Test
  fun lexInt128TypeCombinator() {
    val lexer = lexerForText("int128 4*[ int ] = Int128;")
    lexer.assertNextToken(Tokens.LcIdent)
    lexer.assertNextToken(Tokens.NatConst)
    lexer.assertNextToken(Tokens.Asterisk)
    lexer.assertNextToken(Tokens.OpenBracket)
    lexer.assertNextToken(Tokens.LcIdent)
    lexer.assertNextToken(Tokens.CloseBracket)
    lexer.assertNextToken(Tokens.Equals)
    lexer.assertNextToken(Tokens.UcIdent)
    lexer.assertNextToken(Tokens.Semicolon)
    lexer.assertEof()
  }

  @Test
  fun lexIntTypeCombinatorWithName() {
    val lexer = lexerForText("int#a8509bda ? = Int;")
    lexer.assertNextToken(Tokens.LcIdent)
    lexer.assertNextToken(Tokens.HexConst)
    lexer.assertNextToken(Tokens.QuestMark)
    lexer.assertNextToken(Tokens.Equals)
    lexer.assertNextToken(Tokens.UcIdent)
    lexer.assertNextToken(Tokens.Semicolon)
    lexer.assertEof()
  }
}
