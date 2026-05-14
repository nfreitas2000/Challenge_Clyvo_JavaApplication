
insert into T_CLYVO_SWAGGER_PESSOA(nome, cpf, data_nascimento, email_pessoal) values('Pessoa 1','220.453.840-04','2002-05-05','pessoal1@gmail.com'); 

insert into T_CLYVO_SWAGGER_USER(fk_pessoa, username, senha, permissao, data_criacao) values(1,'RM1','$2a$10$0BhZzH8hG2FEW6qhs9UpV.XlaRPf.ODWDDevV67y3TzXeDzUUaRTK','USER','2026-05-05'); /*SENHA: 1234*/

INSERT INTO T_CLYVO_RESPONSAVEL (nm_responsavel, cpf, dt_nascimento, email, num_celular) VALUES ('Rodrigo Alves da Silva', '11111111111', DATE '1995-01-01', 'rodrigo@gmail.com', '11999999999');

INSERT INTO T_CLYVO_RESPONSAVEL (nm_responsavel, cpf, dt_nascimento, email, num_celular) VALUES ('Amanda Ferreira Costa', '22222222222', DATE '1998-07-20', 'amanda@gmail.com', '21988888888');

INSERT INTO T_CLYVO_VETERINARIO (nm_veterinario, cpf, dt_nascimento, email, num_celular, especialidade) VALUES ('Gabriel Martins', '33333333333', DATE '1988-03-15', 'gabriel@gmail.com', '11977777777', 'Cardiologia');

INSERT INTO T_CLYVO_ANIMAL (nm_animal, idade, especie, raca, sexo, dt_nascimento, peso, tutor_id_tutor) VALUES ('Thor', 5, 'Cachorro', 'Golden Retriever', 'M', DATE '2021-03-10', 32, 1);

INSERT INTO T_CLYVO_DOENCA (nm_doenca, tipo, descricao, contagiosidade) VALUES ('Otite', 'Infecção', 'Infecção no ouvido', 'N');

INSERT INTO T_CLYVO_DOENCA_ANIMAL (dt_diagnostico, animal_id_animal, doenca_id_doenca, gravidade, status, observacoes) VALUES (DATE '2026-05-10', 1, 1, 'Moderada', 'Em tratamento', 'Uso de antibiótico');

INSERT INTO T_CLYVO_CONSULTA (dt_agendamento, dt_consulta, animal_id_animal, veterinario_id_veterinario) VALUES (DATE '2026-05-09', DATE '2026-05-10', 1, 1);

INSERT INTO T_CLYVO_HISTORICO (dt_criacao_historico, num_consultas_clyvo, animal_id_animal, consulta_id_consulta, link_historico) VALUES (DATE '2026-05-10', 1, 1, 1, 'https://historico-animal.com/1');

INSERT INTO T_CLYVO_VACINA (nm_vacina, tipo, descricao, qtd_vacina) VALUES ('V10', 'Canina', 'Vacina múltipla canina', 10);

INSERT INTO T_CLYVO_VACINA_ANIMAL (dt_receita, animal_id_animal, vacinas_id_vacina, instrucao) VALUES (DATE '2026-05-01', 1, 1, 'Aplicar reforço em 30 dias');