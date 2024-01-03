package com.github.lppedd.mtproto.tl.parser

import com.github.lppedd.mtproto.tl.parser.ext.assertEof
import com.github.lppedd.mtproto.tl.parser.ext.assertNextToken
import com.github.lppedd.mtproto.tl.parser.generated.MTProtoTLLexer.Tokens.*
import kotlin.test.Test

/**
 * @author Edoardo Luppi
 */
class MTProtoTLLexerTest : MTProtoTLBaseTest() {
  @Test
  fun lexStringTypeCombinator() {
    val lexer = lexerForText("string ? = String;")
    lexer.assertNextToken(LcIdent.id)
    lexer.assertNextToken(QuestMark.id)
    lexer.assertNextToken(Equals.id)
    lexer.assertNextToken(UcIdent.id)
    lexer.assertNextToken(Semicolon.id)
    lexer.assertEof()
  }

  @Test
  fun lexInt128TypeCombinator() {
    val lexer = lexerForText("int128 4*[ int ] = Int128;")
    lexer.assertNextToken(LcIdent.id)
    lexer.assertNextToken(NatConst.id)
    lexer.assertNextToken(Asterisk.id)
    lexer.assertNextToken(OpenBracket.id)
    lexer.assertNextToken(LcIdent.id)
    lexer.assertNextToken(CloseBracket.id)
    lexer.assertNextToken(Equals.id)
    lexer.assertNextToken(UcIdent.id)
    lexer.assertNextToken(Semicolon.id)
    lexer.assertEof()
  }

  @Test
  fun lexIntTypeCombinatorWithName() {
    val lexer = lexerForText("int#a8509bda ? = Int;")
    lexer.assertNextToken(LcIdent.id)
    lexer.assertNextToken(HexConst.id)
    lexer.assertNextToken(QuestMark.id)
    lexer.assertNextToken(Equals.id)
    lexer.assertNextToken(UcIdent.id)
    lexer.assertNextToken(Semicolon.id)
    lexer.assertEof()
  }
}
