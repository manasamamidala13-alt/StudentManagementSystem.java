import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main {

    // Store student objects
    static ArrayList<Student> students = new ArrayList<>();

    // Input fields
    static JTextField idField;
    static JTextField nameField;
    static JTextField ageField;
    static JTextField courseField;

    // Table
    static JTable table;
    static DefaultTableModel tableModel;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Management System");

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 245));

        frame.add(panel);

        // ================= TITLE =================

        JLabel title = new JLabel("Student Management System");

        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(250, 20, 400, 40);

        panel.add(title);

        // ================= STUDENT ID =================

        JLabel idLabel = new JLabel("Student ID:");

        idLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        idLabel.setBounds(50, 90, 100, 30);

        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(150, 90, 220, 30);

        panel.add(idField);

        // ================= NAME =================

        JLabel nameLabel = new JLabel("Name:");

        nameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        nameLabel.setBounds(50, 130, 100, 30);

        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 130, 220, 30);

        panel.add(nameField);

        // ================= AGE =================

        JLabel ageLabel = new JLabel("Age:");

        ageLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        ageLabel.setBounds(50, 170, 100, 30);

        panel.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(150, 170, 220, 30);

        panel.add(ageField);

        // ================= COURSE =================

        JLabel courseLabel = new JLabel("Course:");

        courseLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        courseLabel.setBounds(50, 210, 100, 30);

        panel.add(courseLabel);

        courseField = new JTextField();
        courseField.setBounds(150, 210, 220, 30);

        panel.add(courseField);

        // ================= BUTTONS =================

        JButton addButton = new JButton("Add");

        addButton.setBounds(420, 90, 110, 35);
        panel.add(addButton);

        JButton updateButton = new JButton("Update");

        updateButton.setBounds(550, 90, 110, 35);
        panel.add(updateButton);

        JButton searchButton = new JButton("Search");

        searchButton.setBounds(420, 140, 110, 35);
        panel.add(searchButton);

        JButton deleteButton = new JButton("Delete");

        deleteButton.setBounds(550, 140, 110, 35);
        panel.add(deleteButton);

        JButton clearButton = new JButton("Clear");

        clearButton.setBounds(485, 190, 110, 35);
        panel.add(clearButton);

        // ================= TABLE =================

        String[] columns = {
                "ID",
                "Name",
                "Age",
                "Course"
        };

        tableModel = new DefaultTableModel(columns, 0);

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBounds(50, 270, 690, 230);

        panel.add(scrollPane);

        // ================= ADD =================

        addButton.addActionListener(e -> addStudent(frame));

        // ================= UPDATE =================

        updateButton.addActionListener(e -> updateStudent(frame));

        // ================= SEARCH =================

        searchButton.addActionListener(e -> searchStudent(frame));

        // ================= DELETE =================

        deleteButton.addActionListener(e -> deleteStudent(frame));

        // ================= CLEAR =================

        clearButton.addActionListener(e -> clearFields());

        // ================= TABLE ROW CLICK =================

        table.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent e) {

                int selectedRow = table.getSelectedRow();

                if (selectedRow >= 0) {

                    idField.setText(
                            tableModel.getValueAt(selectedRow, 0).toString()
                    );

                    nameField.setText(
                            tableModel.getValueAt(selectedRow, 1).toString()
                    );

                    ageField.setText(
                            tableModel.getValueAt(selectedRow, 2).toString()
                    );

                    courseField.setText(
                            tableModel.getValueAt(selectedRow, 3).toString()
                    );
                }
            }
        });

        frame.setVisible(true);
    }

    // =====================================================
    // ADD STUDENT
    // =====================================================

    static void addStudent(JFrame frame) {

        try {

            int id = Integer.parseInt(idField.getText().trim());

            String name = nameField.getText().trim();

            int age = Integer.parseInt(ageField.getText().trim());

            String course = courseField.getText().trim();

            // Validation

            if (name.isEmpty() || course.isEmpty()) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please fill all fields."
                );

                return;
            }

            if (age <= 0 || age > 100) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please enter a valid age."
                );

                return;
            }

            // Check duplicate ID

            for (Student student : students) {

                if (student.getId() == id) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Student ID already exists."
                    );

                    return;
                }
            }

            // Create student object

            Student student =
                    new Student(id, name, age, course);

            // Add to ArrayList

            students.add(student);

            // Add to table

            tableModel.addRow(new Object[]{
                    id,
                    name,
                    age,
                    course
            });

            JOptionPane.showMessageDialog(
                    frame,
                    "Student added successfully!"
            );

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    frame,
                    "ID and Age must be numbers."
            );
        }
    }

    // =====================================================
    // SEARCH STUDENT
    // =====================================================

    static void searchStudent(JFrame frame) {

        try {

            int id = Integer.parseInt(
                    idField.getText().trim()
            );

            for (Student student : students) {

                if (student.getId() == id) {

                    nameField.setText(student.getName());

                    ageField.setText(
                            String.valueOf(student.getAge())
                    );

                    courseField.setText(student.getCourse());

                    JOptionPane.showMessageDialog(
                            frame,
                            "Student found!"
                    );

                    return;
                }
            }

            JOptionPane.showMessageDialog(
                    frame,
                    "Student not found."
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Enter a valid Student ID."
            );
        }
    }

    // =====================================================
    // UPDATE STUDENT
    // =====================================================

    static void updateStudent(JFrame frame) {

        try {

            int id = Integer.parseInt(
                    idField.getText().trim()
            );

            String name = nameField.getText().trim();

            int age = Integer.parseInt(
                    ageField.getText().trim()
            );

            String course = courseField.getText().trim();

            if (name.isEmpty() || course.isEmpty()) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please fill all fields."
                );

                return;
            }

            if (age <= 0 || age > 100) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please enter a valid age."
                );

                return;
            }

            for (int i = 0; i < students.size(); i++) {

                Student student = students.get(i);

                if (student.getId() == id) {

                    // Update object

                    student.setName(name);
                    student.setAge(age);
                    student.setCourse(course);

                    // Update table

                    tableModel.setValueAt(
                            name,
                            i,
                            1
                    );

                    tableModel.setValueAt(
                            age,
                            i,
                            2
                    );

                    tableModel.setValueAt(
                            course,
                            i,
                            3
                    );

                    JOptionPane.showMessageDialog(
                            frame,
                            "Student updated successfully!"
                    );

                    clearFields();

                    return;
                }
            }

            JOptionPane.showMessageDialog(
                    frame,
                    "Student not found."
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    frame,
                    "ID and Age must be numbers."
            );
        }
    }

    // =====================================================
    // DELETE STUDENT
    // =====================================================

    static void deleteStudent(JFrame frame) {

        try {

            int id = Integer.parseInt(
                    idField.getText().trim()
            );

            for (int i = 0; i < students.size(); i++) {

                if (students.get(i).getId() == id) {

                    int result = JOptionPane.showConfirmDialog(
                            frame,
                            "Are you sure you want to delete this student?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (result == JOptionPane.YES_OPTION) {

                        students.remove(i);

                        tableModel.removeRow(i);

                        JOptionPane.showMessageDialog(
                                frame,
                                "Student deleted successfully!"
                        );

                        clearFields();
                    }

                    return;
                }
            }

            JOptionPane.showMessageDialog(
                    frame,
                    "Student not found."
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Enter a valid Student ID."
            );
        }
    }

    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    static void clearFields() {

        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        courseField.setText("");

        table.clearSelection();
    }
}
