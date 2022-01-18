package aaron.excel

import java.io.File
import java.io.PrintStream

class CSVWriter(private val filePath: String) {
    fun write(list: List<String?>) {
        val file = File(filePath)
        file.parentFile.mkdirs()

        // if file does not exist, then create it
        if (!file.exists()) {
            file.createNewFile()
        }

        val ps = PrintStream(file)
        val uft8bom = byteArrayOf(0xef.toByte(), 0xbb.toByte(), 0xbf.toByte())
        ps.write(uft8bom)
        for (s in list) {
            ps.println(s)
        }
        ps.close()
    }
}