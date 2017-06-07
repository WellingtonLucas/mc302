package base;

import io.Escritor;

import java.io.IOException;

/**
 * Created by nael on 5/22/17.
 */
public interface ILaMaSerializable {
    void escreveAtributos(Escritor fw) throws IOException;
}
