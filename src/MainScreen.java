import models.Notas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame{
    private JButton adicionarAlunoButton;
    private JTable table1;
    public JPanel painelMain;
    private JButton listarPorCursoButton;
    private JButton listarPorCursoEButton;
    private JButton melhorAlunoPorCursoButton;
    private JButton melhorAlunoPorAnoButton;
    private JButton apagarRegistosPorAnoButton;
    private JButton apagarRegistosPorCursoButton;
    private JButton apagarRegistosDeAlunoButton;

    private List<Notas> listaNotas = new ArrayList<>();
    private DefaultTableModel tableModel;

    public MainScreen() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nome");
        tableModel.addColumn("Número");
        tableModel.addColumn("Curso");
        tableModel.addColumn("Ano");
        tableModel.addColumn("Nota");

        table1.setModel(tableModel);

        adicionarAlunoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAluno();
            }
        });

        apagarRegistosDeAlunoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeAluno = JOptionPane.showInputDialog("Digite o nome do aluno:");

                apagarRegistrosDeAluno(nomeAluno);
            }
        });

        listarPorCursoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String curso = JOptionPane.showInputDialog("Digite o curso:");

                List<Notas> alunosFiltrados = new ArrayList<>();
                for (Notas aluno : listaNotas) {
                    if (aluno.getCurso().equalsIgnoreCase(curso)) {
                        alunosFiltrados.add(aluno);
                    }
                }

                atualizarTabelaAlunosFiltrados(alunosFiltrados);
            }
        });

        listarPorCursoEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String curso = JOptionPane.showInputDialog("Digite o curso:");
                int ano = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano:"));

                listarPorCursoE(curso, ano);
            }
        });

        melhorAlunoPorCursoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String curso = JOptionPane.showInputDialog("Digite o curso:");

                encontrarMelhorAlunoPorCurso(curso);
            }
        });

        melhorAlunoPorAnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ano = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano:"));

                encontrarMelhorAlunoPorAno(ano);
            }
        });

        apagarRegistosPorAnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ano = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano:"));

                apagarRegistrosPorAno(ano);
            }
        });

        apagarRegistosPorCursoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String curso = JOptionPane.showInputDialog("Digite o curso:");

                apagarRegistrosPorCurso(curso);
            }
        });

    }

    private void adicionarAluno() {
        // Abra um JOptionPane ou uma janela para receber os dados do aluno
        // Crie um objeto Notas com os dados fornecidos
        // Adicione o objeto à lista e atualize a tabela
        // Exemplo simplificado:
        String nome = JOptionPane.showInputDialog("Digite o nome do aluno:");
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do aluno:"));
        String curso = JOptionPane.showInputDialog("Digite o curso do aluno:");
        int ano = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano do aluno:"));
        double nota = Double.parseDouble(JOptionPane.showInputDialog("Digite a nota do aluno:"));

        Notas aluno = new Notas(nome, numero, curso, ano, nota);
        listaNotas.add(aluno);

        // Atualize a tabela
        atualizarTabela();
    }

    private void atualizarTabela() {
        // Limpe o modelo da tabela
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }

        // Atualize as colunas do modelo
        tableModel.setColumnIdentifiers(new Object[]{"Nome", "Número", "Curso", "Ano", "Nota"});
        table1.setModel(tableModel);

        // Preencha a tabela com os dados da lista
        for (Notas aluno : listaNotas) {
            Object[] row = {aluno.getNome(), aluno.getNumero(), aluno.getCurso(), aluno.getAno(), aluno.getNota()};
            tableModel.addRow(row);
        }
    }

    private void listarPorCursoE(String curso, int ano) {
        // Filtra a lista de alunos por curso e ano
        List<Notas> alunosFiltrados = new ArrayList<>();
        for (Notas aluno : listaNotas) {
            if (aluno.getCurso().equalsIgnoreCase(curso) && aluno.getAno() == ano) {
                alunosFiltrados.add(aluno);
            }
        }

        // Atualiza a tabela com os alunos filtrados
        atualizarTabelaAlunosFiltrados(alunosFiltrados);
    }

    private void atualizarTabelaAlunosFiltrados(List<Notas> alunosFiltrados) {
        // Limpe o modelo da tabela
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }

        // Preencha a tabela com os dados dos alunos filtrados
        for (Notas aluno : alunosFiltrados) {
            Object[] row = {aluno.getNome(), aluno.getNumero(), aluno.getCurso(), aluno.getAno(), aluno.getNota()};
            tableModel.addRow(row);
        }
    }

    private void encontrarMelhorAlunoPorCurso(String curso) {
        // Filtra a lista de alunos por curso
        List<Notas> alunosPorCurso = new ArrayList<>();
        for (Notas aluno : listaNotas) {
            if (aluno.getCurso().equalsIgnoreCase(curso)) {
                alunosPorCurso.add(aluno);
            }
        }

        // Encontra o aluno com a maior nota
        Notas melhorAluno = null;
        double maiorNota = Double.MIN_VALUE;
        for (Notas aluno : alunosPorCurso) {
            if (aluno.getNota() > maiorNota) {
                melhorAluno = aluno;
                maiorNota = aluno.getNota();
            }
        }

        if (melhorAluno != null) {
            JOptionPane.showMessageDialog(null,
                    "Melhor aluno em " + curso + ": " + melhorAluno.getNome() + " (Nota: " + melhorAluno.getNota() + ")");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado para o curso especificado.");
        }
    }

    private void encontrarMelhorAlunoPorAno(int ano) {
        // Filtra a lista de alunos por ano
        List<Notas> alunosPorAno = new ArrayList<>();
        for (Notas aluno : listaNotas) {
            if (aluno.getAno() == ano) {
                alunosPorAno.add(aluno);
            }
        }

        // Encontra o aluno com a maior nota
        Notas melhorAluno = null;
        double maiorNota = Double.MIN_VALUE;
        for (Notas aluno : alunosPorAno) {
            if (aluno.getNota() > maiorNota) {
                melhorAluno = aluno;
                maiorNota = aluno.getNota();
            }
        }

        if (melhorAluno != null) {
            JOptionPane.showMessageDialog(null,
                    "Melhor aluno em " + ano + ": " + melhorAluno.getNome() + " (Nota: " + melhorAluno.getNota() + ")");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado para o ano especificado.");
        }
    }

    private void apagarRegistrosPorAno(int ano) {
        // Remove os alunos da lista que possuem o ano especificado
        if (listaNotas.removeIf(aluno -> aluno.getAno() == ano)){
            JOptionPane.showMessageDialog(null,
                    "Alunos do ano " + ano + " removidos com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado para o ano especificado.");
        }

        // Atualiza a tabela
        atualizarTabela();
    }

    private void apagarRegistrosPorCurso(String curso) {
        // Remove os alunos da lista que possuem o curso especificado
        if (listaNotas.removeIf(aluno -> aluno.getCurso().equalsIgnoreCase(curso))){
            JOptionPane.showMessageDialog(null,
                    "Alunos do curso " + curso + " removidos com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado para o curso especificado.");
        }

        // Atualiza a tabela
        atualizarTabela();
    }

    private void apagarRegistrosDeAluno(String nomeAluno) {
        // Remove o aluno da lista pelo nome
        if (listaNotas.removeIf(aluno -> aluno.getNome().equalsIgnoreCase(nomeAluno))){
            JOptionPane.showMessageDialog(null,
                    "Aluno " + nomeAluno + " removido com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado com o nome especificado.");
        }

        // Atualiza a tabela
        atualizarTabela();
    }


    public static void main(String[] args) {
        MainScreen menu = new MainScreen();
        menu.setContentPane(menu.painelMain);
        menu.setTitle("Hello");
        menu.setSize(900, 400);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
