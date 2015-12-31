/*
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the license, or (at your option) any later version.
*/
package org.gjt.jclasslib.structures.attributes

import org.gjt.jclasslib.structures.AbstractStructure
import org.gjt.jclasslib.structures.InvalidByteCodeException

import java.io.DataInput
import java.io.DataOutput
import java.io.IOException

/**
 * Contains common attributes to a local variable table entry structure.

 * @author [Vitor Carreira](mailto:vitor.carreira@gmail.com)
 */
abstract class LocalVariableCommonEntry : AbstractStructure() {

    /**
     * start_pc of this local variable association.
     */
    var startPc: Int = 0

    /**
     * Length in bytes of this local variable association.
     */
    var length: Int = 0

    /**
     * Index of the constant pool entry containing the name of this
     * local variable.
     */
    var nameIndex: Int = 0

    /**
     * Index of the constant pool entry containing the descriptor of this
     * local variable.
     */
    var descriptorOrSignatureIndex: Int = 0

    /**
     * Index of this local variable.
     */
    var index: Int = 0

    @Throws(InvalidByteCodeException::class, IOException::class)
    override fun read(input: DataInput) {
        startPc = input.readUnsignedShort()
        length = input.readUnsignedShort()
        nameIndex = input.readUnsignedShort()
        descriptorOrSignatureIndex = input.readUnsignedShort()
        index = input.readUnsignedShort()

        debugRead()
    }

    @Throws(InvalidByteCodeException::class, IOException::class)
    override fun write(output: DataOutput) {
        output.writeShort(startPc)
        output.writeShort(length)
        output.writeShort(nameIndex)
        output.writeShort(descriptorOrSignatureIndex)
        output.writeShort(index)

        debugWrite()
    }

    override val debugInfo: String
        get() = "with startPc $startPc, length $length, nameIndex $nameIndex, descriptorIndex $descriptorOrSignatureIndex, index $index"

    companion object {
        /**
         * Length in bytes of a local variable association.
         */
        val LENGTH = 10
    }

}
