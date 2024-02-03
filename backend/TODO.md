# TODO List para o projeto do meu website

## Funcionalidades
- [ ] Criação das classes de exceção.
- [ ] Implementar os sistemas de validação.
- [ ] Utilizar o sistema de DTO.
- [ ] Reanalisar arquitetura e possíveis melhorias.
- [ ] Incrementar mais itens para este TODO List.

# Exceções a implementar:

### _**Exceções para UsuarioController (Usuario):**_

* Buscar por ID que não existe:

UsuarioNotFoundException: Quando o ID do usuário fornecido não corresponde a nenhum usuário existente.

* Atualizar com ID que não existe:

UsuarioNotFoundException: Quando tenta atualizar um usuário com um ID inexistente.

* Remover usuário que não existe:

UsuarioNotFoundException: Ao tentar remover um usuário com um ID que não está no banco de dados.

* Remover usuário com posts associados:

UsuarioHasPostsException: Se tentar remover um usuário que ainda tem posts associados. (Ou anonimizar o usuário, como você sugeriu).

* Validação de Entrada:

ValidationException: Para entradas inválidas como IDs negativos, caracteres onde deveriam ser números, etc.

### _Exceções para PostController:_

* Buscar por ID que não existe:

PostNotFoundException: Quando o ID do post fornecido não corresponde a nenhum post existente.

* Atualizar com ID que não existe:

PostNotFoundException: Quando tenta atualizar um post com um ID inexistente.

* Remover post que não existe:

PostNotFoundException: Ao tentar remover um post com um ID que não está no banco de dados.

* Cadastrar post informando autor que não existe:

UsuarioNotFoundException: Quando um post é cadastrado com um ID de usuário que não existe.

* Post ultrapassa limite de caracteres:

PostSizeLimitExceededException: Se o tamanho do texto do post ultrapassar o limite estabelecido.

* Validação de Entrada:

ValidationException: Para entradas inválidas como IDs negativos, caracteres onde deveriam ser números, etc.