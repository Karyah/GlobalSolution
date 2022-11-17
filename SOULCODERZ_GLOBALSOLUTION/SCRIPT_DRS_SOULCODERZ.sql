/*
Uma consulta simples envolvendo SELECT/FROM/WHERE/ORDER BY.

Consulta que encontra a palavra "Rua" em qualquer lugar da string do endereço do ponto, 
e mostra tais resultados.
Tal busca deve ser ordenada pelo id do ponto (idPonto)*/
select * from Ponto where endereco like '%Rua%' order by idPonto;


/*Uma  consulta  envolvendo  uma  ou  mais  junções  de  tabela,  contendo: SELECT/FROM/WHERE/ORDER BY

Consulta que junta as tabelas Ponto e Usuario. Objetivo: retornar endereços que comecem com R. Além de retornar os endereços do ponto
que fazem parte dessa análise, retorna também o nome do usuário que criou este ponto, de maneira que essa consulta fique mais organizada.
*/
select usuario.nome as Dono, ponto.endereco
from Ponto join Usuario on (usuario.idusuario = ponto.idusuario)
where ponto.endereco like 'R%'
order by usuario.nome;

/*Uma consulta envolvendo função de grupo e agrupamento

Consulta que tem como objetivo ordenar o aluguel pelo tempo mínimo de uso. Ao juntar
as tabelas Bicicleta e Aluguel, podemos mostrar junto com os menores tempos, 
o respectivo serial das bicicletas.
*/

select bicicleta.serial, min(aluguel.tempoDeUso)
from Aluguel join Bicicleta using (idBicicleta)
group by serial
order by min(aluguel.tempoDeUso);

/*Uma consulta envolvendo função de grupo, agrupamento com filtro (having) e junção de tabelas

Consulta que tem como objetivo retornar o tempo de uso do aluguel, que seja maior que 100 minutos.
Ao juntar as tabelas Aluguel e Usuario, podemos ver também qual o nome do usuário que realizou o 
alguel da bicicleta.
*/
select usuario.nome, aluguel.idUsuario, max(tempoDeUso) as tempo
from Aluguel join Usuario on (usuario.idusuario = aluguel.idusuario) 
group by aluguel.idUsuario, usuario.nome
having max(tempoDeUso) > 100;

