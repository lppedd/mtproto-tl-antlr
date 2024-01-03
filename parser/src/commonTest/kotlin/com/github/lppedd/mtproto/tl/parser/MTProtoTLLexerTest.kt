package com.github.lppedd.mtproto.tl.parser

import com.github.lppedd.mtproto.tl.parser.ext.assertEof
import com.github.lppedd.mtproto.tl.parser.ext.assertNextToken
import com.github.lppedd.mtproto.tl.parser.generated.MTProtoTLLexer
import com.github.lppedd.mtproto.tl.parser.generated.MTProtoTLLexer.Tokens.*
import com.github.lppedd.mtproto.tl.parser.util.ThrowingErrorListener
import org.antlr.v4.kotlinruntime.CharStreams
import kotlin.test.Test

/**
 * @author Edoardo Luppi
 */
class MTProtoTLLexerTest {
  private fun lexerForText(text: String): MTProtoTLLexer {
    val lexer = MTProtoTLLexer(CharStreams.fromString(text))
    lexer.removeErrorListeners()
    lexer.addErrorListener(ThrowingErrorListener)
    return lexer
  }

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
