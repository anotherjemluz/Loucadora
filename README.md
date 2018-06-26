# Loucadora
## Um pequeno sistema para gerir locadoras extintas

![diagrama de classe do sistema](ldc.png)

Para utilizar em sua máquina é necessário ter o node.js instalado, para poder as depêndencias com npm install.

Após adquirir as dependências, uma delas é o live-server, que permite visualização da interface ao executar um servidor local na sua máquina. Certifique-se de estar conectado à uma mesma rede wifi pelo computador e pelo celular (onde a visualização é recomendada, pois a interface só foi implementada para mobile), e com o endereço ipv4 da rede, execute o seguinte comando pelo prompt na pasta raiz do repositório: live-server --host=seuipv4. O servidor irá iniciar e abrir o navegador automaticamente no computador, e para visualizar no celular, basta copiar o endereço do ip+porta+web (semelhante a http://112.558.0.14:8080/web/index.html)

Descobrir o ipv4 da sua rede no windows10: ctrl + alt + del > gerenciador de tarefas > aba desempenho > sessão wifi ativa (que exibe troca de dados) > na descrição há "Endereço Ipv4" com o ip logo em seguida.

Descobrir o ipv4 da sua rede no mac: não sei, não tenho mac.

Primeiramente é criar criar uma base de dados para alocar os valores de filme, aluguel, cliente e funcionário. O arquivo com o script para o banco de dados se chama “locadora.sql”.

Ao executar o programa, uma tela inicial irá aparecer com opções para o usuário, algumas necessitam da permissão de um funcionário que seja gerente. As opções que apenas gerentes podem fazer são: inserir cliente, excluir cliente ou funcionário, alterar algum dado em alguma tabela (não sei se to esquecendo algo).
