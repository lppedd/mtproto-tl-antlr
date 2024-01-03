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
    val text = "schema-example.tl".loadText()
    val parser = parserForText(text)
    val schema = parser.tlSchema()
    Trees.toStringTree(schema, parser)
  }

  @Test
  fun parseSchemaLayer158() {
    // https://core.telegram.org/schema
    val text = "schema-158.tl".loadText()
    val parser = parserForText(text)
    val schema = parser.tlSchema()
    Trees.toStringTree(schema, parser)
  }

  @Test
  fun parseSchemaLayer170() {
    // https://core.telegram.org/schema
    val text = "schema-170.tl".loadText()
    val parser = parserForText(text)
    val schema = parser.tlSchema()
    Trees.toStringTree(schema, parser)
  }
}
