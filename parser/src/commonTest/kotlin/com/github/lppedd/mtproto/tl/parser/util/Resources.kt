package com.github.lppedd.mtproto.tl.parser.util

import com.goncalossilva.resources.Resource

/**
 * Loads a test resource, typically located under the `/resources` directory, as a byte sequence.
 *
 * @author Edoardo Luppi
 * @see loadBytes
 */
fun loadResourceBytes(path: String): ByteArray {
  val resource = Resource(path)
  return resource.readBytes()
}

/**
 * Loads a test resource, typically located under the `/resources` directory, as UTF-8 encoded text.
 *
 * @author Edoardo Luppi
 * @see loadText
 */
fun loadResourceText(path: String): String {
  val resource = Resource(path)
  return resource.readText()
}

/**
 * Utility extension function to load bytes from a string representing a resource path.
 *
 * @author Edoardo Luppi
 * @see loadResourceBytes
 */
fun String.loadBytes(basePath: String = "src/commonTest/resources"): ByteArray =
  loadResourceBytes("$basePath/$this")

/**
 * Utility extension function to load text from a string representing a resource path.
 *
 * @author Edoardo Luppi
 * @see loadResourceText
 */
fun String.loadText(basePath: String = "src/commonTest/resources"): String =
  loadResourceText("$basePath/$this")
