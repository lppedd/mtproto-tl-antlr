package com.github.lppedd.mtproto.tl.parser.ext

import org.antlr.v4.kotlinruntime.Lexer
import org.antlr.v4.kotlinruntime.Token
import kotlin.test.assertEquals
import kotlin.test.fail

/**
 * Lex and skips `n` tokens, failing if EOF is reached before skipping all of them.
 *
 * @author Edoardo Luppi
 */
@Suppress("unused")
fun Lexer.skipTokens(count: Int, skippedText: String? = null) {
  val sb = StringBuilder(64)

  for (i in 0..<count) {
    val token = nextToken()

    if (token.type == Token.EOF) {
      fail("The lexer reached EOF prematurely at skipped token $i out of $count")
    }

    sb.append(token.text)
  }

  if (skippedText != null) {
    assertEquals(skippedText, sb.toString())
  }
}

/**
 * Asserts that the next lexed token is of a specific type.
 *
 * @author Edoardo Luppi
 */
fun Lexer.assertNextToken(tokenType: Int): Unit =
  assertEquals(tokenType, nextToken().type, "The token type does not match")

/**
 * Asserts that the lexer reached EOF, and no tokens (apart from EOF) are available.
 *
 * @author Edoardo Luppi
 */
fun Lexer.assertEof(): Unit =
  assertEquals(Token.EOF, nextToken().type, "The lexer is not at the end of file")
