/*Consulta que encontra a palavra "Rua" em qualquer lugar da string do endereço do ponto, 
e mostra tais resultados.
Tal busca deve ser ordenada pelo id do ponto (idPonto)*/
select * from Ponto where endereco like '%Rua%' order by idPonto;

