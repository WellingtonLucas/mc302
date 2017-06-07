package io;

import util.Util;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by nael on 5/23/17.
 */
public class Escritor {
    private FileWriter fw;

    public Escritor()
    {
        fw = null;
    }
    public Escritor(String nomeArquivo) throws IOException {
        fw = new FileWriter(nomeArquivo);
    }
    public void escreveAtributo(String nomeAtributo, String valor) throws IOException {
        fw.write(nomeAtributo + Util.DELIM + valor + "\n");
    }
    public void escreveDelimObj(String nomeObjeto) throws IOException {
        fw.write("obj" + Util.DELIM + nomeObjeto + "\n");
    }
    public void finalizar() throws IOException {
        fw.close();
    }
}
