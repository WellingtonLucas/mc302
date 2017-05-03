# Answers for the 6th written assignment (in Portuguese)

2.3.1-2.3.4. Respostas estão disponíveis ao executar o código contido em `src/`.

2.3.5. Sim. A evidência de que isto é verdade é apresentada no código, onde 
`Collection#add(T el)` é chamado repetidas vezes sobre um mesmo elemento e seu
retorno é sempre `true`, indicando que a inserção foi feita.

Todos estes implementam a interface `List<T>`, uma lista de QUAISQUER elementos
(inclusive repetidos) da classe T.

3.3.1-3.3.2. Respostas estão disponíveis ao executar o código contido em `src/`.

3.3.3. Não. A evidência de que isto é verdade é apresentada no código, onde
`Collection#add(T el)` é chamado repetidas vezes sobre um mesmo elemento,
retornando `true` na primeira invocação e `false` em todas as subsequentes.

Não existe repetições em um conjunto matemático (i.e., {1,2,3,4} + {2} = {1,2,3,4}).
Portanto, todas as implementações da interface `Set` devem obrigatoriamente
recusar repetições.
