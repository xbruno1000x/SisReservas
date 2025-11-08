-- Inserir clientes de exemplo
INSERT INTO clientes (nome, telefone, email, criado_em) VALUES
('João Silva', '11987654321', 'joao.silva@email.com', CURRENT_TIMESTAMP),
('Maria Santos', '11976543210', 'maria.santos@email.com', CURRENT_TIMESTAMP),
('Pedro Oliveira', '11965432109', 'pedro.oliveira@email.com', CURRENT_TIMESTAMP),
('Ana Costa', '11954321098', 'ana.costa@email.com', CURRENT_TIMESTAMP),
('Carlos Souza', '11943210987', 'carlos.souza@email.com', CURRENT_TIMESTAMP),
('Juliana Pereira', '11932109876', 'juliana.pereira@email.com', CURRENT_TIMESTAMP),
('Roberto Alves', '11921098765', 'roberto.alves@email.com', CURRENT_TIMESTAMP),
('Fernanda Lima', '11910987654', 'fernanda.lima@email.com', CURRENT_TIMESTAMP),
('Lucas Rodrigues', '11909876543', 'lucas.rodrigues@email.com', CURRENT_TIMESTAMP),
('Patricia Martins', '11898765432', 'patricia.martins@email.com', CURRENT_TIMESTAMP);

-- Inserir reservas de exemplo
INSERT INTO reservas (cliente_id, data, hora, observacoes, status, criado_em, atualizado_em) VALUES
-- Reservas para hoje e próximos dias
(1, CURRENT_DATE, '09:00:00', 'Corte de cabelo masculino', 'CONFIRMADA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, CURRENT_DATE, '10:30:00', 'Manicure e pedicure', 'CONFIRMADA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, CURRENT_DATE, '14:00:00', 'Corte e barba', 'PENDENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, CURRENT_DATE, '16:00:00', 'Hidratação capilar', 'CONFIRMADA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Reservas para amanhã
(5, DATEADD('DAY', 1, CURRENT_DATE), '09:30:00', 'Corte infantil', 'CONFIRMADA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, DATEADD('DAY', 1, CURRENT_DATE), '11:00:00', 'Esmaltação em gel', 'PENDENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, DATEADD('DAY', 1, CURRENT_DATE), '13:30:00', 'Barba e sobrancelha', 'CONFIRMADA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, DATEADD('DAY', 1, CURRENT_DATE), '15:00:00', 'Escova e penteado', 'PENDENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Reservas para depois de amanhã
(9, DATEADD('DAY', 2, CURRENT_DATE), '10:00:00', 'Corte degradê', 'CONFIRMADA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, DATEADD('DAY', 2, CURRENT_DATE), '14:30:00', 'Spa dos pés', 'PENDENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, DATEADD('DAY', 2, CURRENT_DATE), '16:30:00', 'Luzes e corte', 'CONFIRMADA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Reservas futuras (próxima semana)
(2, DATEADD('DAY', 7, CURRENT_DATE), '09:00:00', 'Manutenção de unhas', 'PENDENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, DATEADD('DAY', 7, CURRENT_DATE), '11:00:00', 'Corte social', 'PENDENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, DATEADD('DAY', 8, CURRENT_DATE), '10:00:00', 'Coloração completa', 'PENDENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, DATEADD('DAY', 9, CURRENT_DATE), '15:00:00', 'Corte e escova', 'PENDENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Reservas passadas (para histórico)
(6, DATEADD('DAY', -1, CURRENT_DATE), '09:00:00', 'Design de sobrancelhas', 'CONCLUIDA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, DATEADD('DAY', -1, CURRENT_DATE), '14:00:00', 'Corte e barba', 'CONCLUIDA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, DATEADD('DAY', -2, CURRENT_DATE), '10:30:00', 'Manicure simples', 'CONCLUIDA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, DATEADD('DAY', -3, CURRENT_DATE), '16:00:00', 'Corte feminino', 'CONCLUIDA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Algumas reservas canceladas
(10, DATEADD('DAY', -1, CURRENT_DATE), '11:00:00', 'Cliente cancelou', 'CANCELADA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, DATEADD('DAY', 5, CURRENT_DATE), '14:00:00', 'Reagendamento solicitado', 'CANCELADA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
