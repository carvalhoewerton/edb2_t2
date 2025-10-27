Como compilar o programa:
1)É necessário ter o Java 21 instalado na sua máquina e fazer o clone do repositório. Para compilar, vá para o local do diretório e digite:
<b>javac -d bin (Get-ChildItem -Recurse -Filter *.java).FullName</b>

2)Não foram utilizadas dependências externas. Todas as classes utilizadas no programa são padrões do Java.

3)As operações que foram implementadas são: impressão, inserção, remoção e busca.

4)Para executar o programa, abra o terminal e digite:
<b>java -cp bin MainApplication avl</b>

O programa exibirá a impressão da árvore conforme os métodos são executados, mostrando visualmente como a estrutura se modifica após cada operação. Ao final, será exibida uma tabela com o tempo de execução de cada método, permitindo acompanhar a eficiência das operações.

<img width="344" height="285" alt="image" src="https://github.com/user-attachments/assets/e18c8d23-8374-45d3-95e7-bf5c698b1762" /> <img width="442" height="147" alt="image" src="https://github.com/user-attachments/assets/be80c3ff-339c-4425-8642-c15cc1b2fab2" />
