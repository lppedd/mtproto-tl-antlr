package com.github.lppedd.mtproto.tl.parser

import com.github.lppedd.mtproto.tl.parser.generated.MTProtoTLLexer
import com.github.lppedd.mtproto.tl.parser.generated.MTProtoTLParser
import com.github.lppedd.mtproto.tl.parser.util.ThrowingErrorListener
import com.github.lppedd.mtproto.tl.parser.util.loadText
import org.antlr.v4.kotlinruntime.CharStreams
import org.antlr.v4.kotlinruntime.CommonTokenStream
import org.antlr.v4.kotlinruntime.tree.Trees
import kotlin.test.Test

/**
 * @author Edoardo Luppi
 */
class MTProtoTLParserTest {
  private fun parserForText(text: String): MTProtoTLParser {
    val lexer = MTProtoTLLexer(CharStreams.fromString(text))
    lexer.removeErrorListeners()
    lexer.addErrorListener(ThrowingErrorListener)

    val parser = MTProtoTLParser(CommonTokenStream(lexer))
    parser.removeErrorListeners()
    parser.addErrorListener(ThrowingErrorListener)
    return parser
  }

  @Test
  fun parseExampleSchema() {
    // https://core.telegram.org/mtproto/TL#example
    val text = "schema-example.txt".loadText()
    val parser = parserForText(text)
    val schema = parser.tlSchema()
    Trees.toStringTree(schema, parser)
  }

  @Test
  fun parseSchema158() {
    // https://core.telegram.org/schema
    val text = "schema-158.txt".loadText()
    val parser = parserForText(text)
    val schema = parser.tlSchema()
    Trees.toStringTree(schema, parser)
  }
}
