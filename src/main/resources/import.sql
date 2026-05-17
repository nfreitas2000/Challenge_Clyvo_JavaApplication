insert into T_CLYVO_SWAGGER_PESSOA(nome, cpf, data_nascimento, email_pessoal) values('Pessoa 1','52998224725','2002-05-05','pessoal1@gmail.com');

insert into T_CLYVO_SWAGGER_USER(fk_pessoa, username, senha, permissao, data_criacao) values(1,'RM1','$2a$10$0BhZzH8hG2FEW6qhs9UpV.XlaRPf.ODWDDevV67y3TzXeDzUUaRTK','USER','2026-05-05'); /*SENHA: 1234*/

INSERT INTO T_CLYVO_RESPONSAVEL (nm_responsavel, cpf, dt_nascimento, email, num_celular) VALUES ('Rodrigo Alves da Silva', '529.982.247-25', DATE '1995-01-01', 'rodrigo@gmail.com', '+55 (11) 99999-9999');

INSERT INTO T_CLYVO_RESPONSAVEL (nm_responsavel, cpf, dt_nascimento, email, num_celular) VALUES ('Amanda Ferreira Costa', '168.995.350-09', DATE '1998-07-20', 'amanda@gmail.com', '+55 (21) 98888-8888');

INSERT INTO T_CLYVO_VETERINARIO (nm_veterinario, cpf, dt_nascimento, email, num_celular, especialidade) VALUES ('Gabriel Martins', '295.379.148-06', DATE '1988-03-15', 'gabriel@gmail.com', '+55 (11) 97777-7777', 'Cardiologia');

INSERT INTO T_CLYVO_ANIMAL (nm_animal, idade, especie, raca, sexo, dt_nascimento, peso, Responsavel_id_responsavel) VALUES ('Thor', 5, 'Cachorro', 'Golden Retriever', 'M', DATE '2021-03-10', 32, 1);

INSERT INTO T_CLYVO_DOENCA (nm_doenca, tipo, descricao, contagiosidade) VALUES ('Otite', 'Infecção', 'Infecção no ouvido', 'N');

INSERT INTO T_CLYVO_DOENCA_ANIMAL (dt_diagnostico, animal_id_animal, doenca_id_doenca, gravidade, status, observacoes) VALUES (DATE '2026-05-10', 1, 1, 'Moderada', 'Em tratamento', 'Uso de antibiótico');

INSERT INTO T_CLYVO_CONSULTA (dt_agendamento, dt_consulta, animal_id_animal, veterinario_id_veterinario) VALUES (DATE '2026-05-09', DATE '2026-05-10', 1, 1);

INSERT INTO T_CLYVO_HISTORICO (dt_criacao_historico, num_consultas_clyvo, animal_id_animal, consulta_id_consulta, link_historico) VALUES (DATE '2026-05-10', 1, 1, 1, 'https://historico-animal.com/1');

INSERT INTO T_CLYVO_VACINA (nm_vacina, tipo, descricao) VALUES ('V10', 'Canina', 'Vacina múltipla canina');

INSERT INTO T_CLYVO_VACINA_ANIMAL (num_doses, frequencia_aplicacao, dt_receita, Animal_id_animal, Vacinas_id_vacina) VALUES (3, 30, DATE '2026-05-01', 1, 1);