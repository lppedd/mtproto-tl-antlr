package com.github.lppedd.mtproto.tl.parser

import com.github.lppedd.mtproto.tl.parser.generated.MTProtoTLLexer
import com.github.lppedd.mtproto.tl.parser.generated.MTProtoTLParser
import com.github.lppedd.mtproto.tl.parser.util.ThrowingErrorListener
import org.antlr.v4.kotlinruntime.CharStreams
import org.antlr.v4.kotlinruntime.CommonTokenStream

/**
 * @author Edoardo Luppi
 */
abstract class MTProtoTLBaseTest {
  open fun lexerForText(text: String): MTProtoTLLexer {
    val lexer = MTProtoTLLexer(CharStreams.fromString(text))
    lexer.removeErrorListeners()
    lexer.addErrorListener(ThrowingErrorListener)
    return lexer
  }

  open fun parserForText(text: String): MTProtoTLParser {
    val lexer = lexerForText(text)
    val parser = MTProtoTLParser(CommonTokenStream(lexer))
    parser.removeErrorListeners()
    parser.addErrorListener(ThrowingErrorListener)
    return parser
  }
}
