
listarArtigos();

function listarArtigos() {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/gerenciar-artigos/listarTodos",
    contentType: "application/json; charset=utf-8",
    success: function (response) {
      for (let i = 0; i < response.length; i++) {
        $('#tabelaArtigos > tbody').append('<tr id="' + response[i].id + '"><td>' + response[i].id + '</td><td>' + response[i].titulo + '</td><td> <button class = "btn btn-warning" onclick = editarArtigo()>Editar</button><button class = "btn btn-danger" onclick = deletarArtigo(' + response[i].id + ')>Deletar</button></td></tr>');
      }
    },
  }).fail(function (xhr, status, errorThrown) {
    alert("Erro ao buscar artigo: " + xhr.responseText);
  });
}

function postarArtigo(event) {
  event.preventDefault();

  const titulo = $("#titulo").val();
  const subtitulo = $("#subtitulo").val();
  const texto = $("#texto").val();
  const imagem = $("#imagem").get(0).files[0];

  const formData = new FormData();
  formData.append("titulo", titulo);
  formData.append("subtitulo", subtitulo);
  formData.append("texto", texto);
  if (imagem) {
    formData.append("imagem", imagem);
  }

  $.ajax({
    method: "POST",
    url: "http://localhost:8080/gerenciar-artigos/novo",
    data: formData,
    contentType: false, // Não definir o tipo de conteúdo, pois o FormData define automaticamente o tipo de conteúdo correto
    processData: false, // Não processar os dados, pois o FormData já está lidando com isso
    success: function (response) {
      // Ação em caso de sucesso
    },
  }).fail(function (xhr, status, errorThrown) {
    // Adicione ações caso a requisição falhe
  });
}

function deletarArtigo(id) {
  event.preventDefault();

  Swal.fire({
    title: "Você realmente deseja excluir este artigo?",
    showCancelButton: true,
    confirmButtonText: "Sim, desejo excluir",
  }).then((result) => {
    if (result.isConfirmed) {
      $.ajax({
        method: "DELETE",
        url: "http://localhost:8080/gerenciar-artigos/deletar/" + id,
        success: function (response) {
          Swal.fire("Artigo excluído com sucesso!", "", "success");
          $("#" + id).remove();
        },
      }).fail(function (xhr, status, errorThrown) {
        Swal.fire({
          icon: "error",
          title: "Oops...",
          text: "Erro ao excluir o artigo!",
        });
      });
    } else if (result.isDenied) {
      Swal.fire("Changes are not saved", "", "info");
    }
  })
}