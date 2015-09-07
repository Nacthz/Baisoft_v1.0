package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import db.MySQLConnection;
import frame.Info;
import views.Sincroautos;
import views.Sincrorepuestos;

public class TableRow extends JPanel {

	private int height = 25;
	private String uid = "";
	@SuppressWarnings("unused")
	private JPanel jFather;
	private int index = 0;
	private boolean edit, first = true;
	private static final long serialVersionUID = 6359330881452231410L;
	private JLabel id;
	private JCheckBox check;
	private JPanel panel_CENTER_CENTE, panel_CENTER;
	private ArrayList<JTextComponent> info = new ArrayList<JTextComponent>();
	private String[] backup = {};
	private Table father;

	public TableRow(JPanel jFather, String[] data, int index, Table father, boolean flag) {
		this.father = father;
		this.jFather = jFather;
		this.index = index;
		backup = data.clone();
		addMouseListener(mouseEvent());
		setLayout(new BorderLayout(0, 0));
		setBackground(new Color(255, 255, 255));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		setMinimumSize(new Dimension(700, height));

		JPanel panel_WEST = new JPanel();
		panel_WEST.setOpaque(false);
		panel_WEST.setBorder(new EmptyBorder(0, 10, 0, 0));
		add(panel_WEST, BorderLayout.WEST);
		panel_WEST.setLayout(new BorderLayout(0, 0));

		if (!father.father.equals("sincrorepuestos_search") &&  !father.father.equals("sincroautos_employee") ) {
			check = new JCheckBox();
			check.addMouseListener(mouseEvent());
			check.setOpaque(false);
			panel_WEST.add(check, BorderLayout.WEST);
		}
		id = new JLabel(data[0]);
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setPreferredSize(new Dimension(35, height));
		panel_WEST.add(id, BorderLayout.CENTER);

		JLabel line_SOUTH = new JLabel();
		line_SOUTH.setPreferredSize(new Dimension(Integer.MAX_VALUE, 1));
		line_SOUTH.setBackground(new Color(220, 220, 220));
		line_SOUTH.setOpaque(true);
		add(line_SOUTH, BorderLayout.SOUTH);

		panel_CENTER = new JPanel();
		panel_CENTER.setOpaque(false);
		add(panel_CENTER, BorderLayout.CENTER);
		panel_CENTER.setLayout(new BorderLayout(0, 0));

		panel_CENTER_CENTE = new JPanel();
		panel_CENTER_CENTE.setOpaque(false);
		panel_CENTER.add(panel_CENTER_CENTE, BorderLayout.CENTER);
		panel_CENTER_CENTE.setLayout(new GridLayout(1, 0, 0, 0));

		id.setFont(new Font("Tahoma", Font.BOLD, 12));

		JPanel panel_EAST = new JPanel();
		panel_EAST.setBorder(new EmptyBorder(0, 0, 0, 10));
		panel_EAST.setOpaque(false);

		if (!father.father.equals("sincrorepuestos_search") &&  !father.father.equals("sincroautos_employee") ) {
			if (flag) {
				panel_EAST.setPreferredSize(new Dimension(100, height));
				panel_EAST.setLayout(new GridLayout(0, 4, 0, 0));
			} else {
				panel_EAST.setPreferredSize(new Dimension(50, height));
				panel_EAST.setLayout(new GridLayout(0, 2, 0, 0));
			}
		} else {
			panel_EAST.setPreferredSize(new Dimension(30, height));
			panel_EAST.setLayout(new GridLayout(0, 1, 0, 0));
		}

		add(panel_EAST, BorderLayout.EAST);

		for (int i = 1; i < data.length; i++) {
			addCamp(data[i]);
		}

		if (!father.father.equals("sincrorepuestos_search") &&  !father.father.equals("sincroautos_employee") ) {
			JLabel JL_delete;
			if (data.length > 2) {
				if (data[2].equals("eliminado"))
					JL_delete = new JLabel(new ImageIcon("img/fileback.png"));
				else
					JL_delete = new JLabel(new ImageIcon("img/trash.png"));
			} else {
				JL_delete = new JLabel(new ImageIcon("img/trash.png"));
			}

			JLabel JL_edit = new JLabel(new ImageIcon("img/pencil.png"));
			JL_delete.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (!data[2].equals("eliminado")) {
						if (edit) {
							edit = !edit;
							int i = 1;
							for (JTextComponent ob : info) {
								ob.setText(backup[i]);
								ob.setFont(new Font("Tahoma", Font.PLAIN, 12));
								ob.setEditable(edit);
								i++;
							}

							JL_edit.setIcon(new ImageIcon("img/pencil.png"));
							JL_delete.setIcon(new ImageIcon("img/trash.png"));
						} else {
							doDelete();
						}
					} else
						doEnable();
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					if (!edit)
						setBackground(new Color(230, 230, 230));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					if (!edit)
						setBackground(new Color(255, 255, 255));
				}
			});
			JL_delete.setOpaque(false);
			JL_edit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					edit = !edit;		
					if (edit) {
						int i = 0;
						for (JTextComponent ob : info) {
							ob.setFont(new Font("Tahoma", Font.PLAIN, 16));

							if (father.father.equals("sincrorepuestos")) {
								i++;
								if (!(i == 1 || i == 3 || i == 4 || i == 6))
									ob.setEditable(edit);
							} else {
								ob.setEditable(edit);
							}

						}
						JL_edit.setIcon(new ImageIcon("img/tick.png"));
						JL_delete.setIcon(new ImageIcon("img/cross.png"));

					} else {
						int i = 1, a = 0, b = 0;
						JTextComponent temp = null;
						for (JTextComponent ob : info) {
							ob.setFont(new Font("Tahoma", Font.PLAIN, 12));
							ob.setEditable(edit);
							backup[i] = ob.getText();

							if (father.father.equals("sincrorepuestos")) {
								if (i == 2) {
									a = Integer.parseInt(backup[i]);
								}
								if (i == 3) {
									b = Integer.parseInt(backup[i]);
								}

								if (i == 4) {
									temp = ob;
								}
							}
							i++;
						}

						if (father.father.equals("sincrorepuestos")) {
							temp.setText("" + (a * b - Integer.parseInt(backup[5])));
							backup[4] = temp.getText();
						}

						father.updateRow(index, backup);
						JL_edit.setIcon(new ImageIcon("img/pencil.png"));
						JL_delete.setIcon(new ImageIcon("img/trash.png"));
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					if (!edit)
						setBackground(new Color(230, 230, 230));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					if (!edit)
						setBackground(new Color(255, 255, 255));
				}
			});

			if (flag) {
				JLabel JL_download = new JLabel(new ImageIcon("img/download.png"));
				JL_download.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						System.out.println("Descargar");
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						if (!edit)
							setBackground(new Color(230, 230, 230));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						if (!edit)
							setBackground(new Color(255, 255, 255));
					}
				});
				JLabel JL_view = new JLabel(new ImageIcon("img/views.png"));
				JL_view.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (father.father.equals("Bill_2")) {
							ArrayList<String[]> result = MySQLConnection.getBillInfo2(backup[4]);
							
							final Info inf = new Info(backup[4], result, "Bill_2");
							inf.setVisible(true);
						}
						
						if (father.father.equals("Bill_1")) {
							ArrayList<String[]> result = MySQLConnection.getBillInfo1(backup[6]);
							
							final Info inf = new Info(backup[5], result, "Bill_1");
							inf.setVisible(true);
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						if (!edit)
							setBackground(new Color(230, 230, 230));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						if (!edit)
							setBackground(new Color(255, 255, 255));
					}
				});
				JLabel JL_print = new JLabel(new ImageIcon("img/print.png"));
				JL_print.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						System.out.println("Imprimir");
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						if (!edit)
							setBackground(new Color(230, 230, 230));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						if (!edit)
							setBackground(new Color(255, 255, 255));
					}
				});
				panel_EAST.add(JL_print);
				panel_EAST.add(JL_view);
				panel_EAST.add(JL_download);
			}else
				panel_EAST.add(JL_edit);
			
			panel_EAST.add(JL_delete);
			
			if(father.father.equals("Info")){
				JL_edit.setVisible(false);
				JL_delete.setVisible(false);
			}
		} else {
			JLabel JL_target;
			JL_target = new JLabel(new ImageIcon("img/target.png"));
			JL_target.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(father.father.equals("sincroautos_employee")){
						Sincroautos sa = (Sincroautos) jFather;
						sa.selectEmployee(backup[0], backup[1] );							
					}
					
					if(father.father.equals("sincrorepuestos_search")){
						Sincrorepuestos sr = (Sincrorepuestos) jFather;
						sr.addRow(backup[0]);					
					}					

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					if (!edit)
						setBackground(new Color(230, 230, 230));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					if (!edit)
						setBackground(new Color(255, 255, 255));
				}
			});
			panel_EAST.add(JL_target);
		}
	}

	public void addCamp(String name) {
		JTextField JTF = new JTextField(name);
		JTF.addMouseListener(mouseEvent());
		JTF.setOpaque(false);
		JTF.setBorder(null);
		JTF.setEditable(false);
		JTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		info.add(JTF);

		if (first) {
			JTF.setPreferredSize(new Dimension(230, height));
			panel_CENTER.add(JTF, BorderLayout.WEST);
			first = false;
			return;
		}

		JTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel_CENTER_CENTE.add(JTF);
	}

	public MouseAdapter mouseEvent() {
		return new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (!edit)
					setBackground(new Color(230, 230, 230));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!edit)
					setBackground(new Color(255, 255, 255));
			}
		};
	}

	public void setSelected(boolean status) {
		check.setSelected(status);
	}

	public void doEnable() {
		String message = "¿Esta seguro que desea habilitar este elemento?";
		String title = "Habilitando: " + backup[1];
		// display the JOptionPane showConfirmDialog
		int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			father.enableRow(index, backup[0]);
		}
	}

	public void doDelete() {
		String message = "¿Esta seguro que desea eliminar este elemento?";
		String title = "Eliminando: " + backup[1];
		// display the JOptionPane showConfirmDialog
		int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			if (uid.equals(""))
				father.deleteRow(index, backup[0]);
			else
				father.deleteRow(this, uid);
		}
	}

	public String[] getDescription() {
		if (father.father.equals("sincrorepuestos")) {
			return new String[] { backup[0], backup[2], backup[5], backup[6] };
		}
		
		if (father.father.equals("sincroautos")) {
			return new String[] { backup[0], backup[1], backup[2] };
		}
		
		return null;
	}

	public int getValue() {
		if (father.father.equals("sincrorepuestos")) {
			return Integer.parseInt(backup[6]);
		}
		if (father.father.equals("sincroautos")) {
			return Integer.parseInt(backup[2]);
		}
		return 0;
	}

	public boolean isSelected() {
		return check.isSelected();
	}

	public TableRow(JPanel jFather, String[] data, int index, Table father, boolean flag, String uid) {
		this.uid = uid;
		this.father = father;
		this.jFather = jFather;
		this.index = index;
		backup = data.clone();
		addMouseListener(mouseEvent());
		setLayout(new BorderLayout(0, 0));
		setBackground(new Color(255, 255, 255));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		setMinimumSize(new Dimension(700, height));

		JPanel panel_WEST = new JPanel();
		panel_WEST.setOpaque(false);
		panel_WEST.setBorder(new EmptyBorder(0, 10, 0, 0));
		add(panel_WEST, BorderLayout.WEST);
		panel_WEST.setLayout(new BorderLayout(0, 0));

		if (!father.father.equals("sincrorepuestos_search")) {
			check = new JCheckBox();
			check.addMouseListener(mouseEvent());
			check.setOpaque(false);
			panel_WEST.add(check, BorderLayout.WEST);
		}
		id = new JLabel(data[0]);
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setPreferredSize(new Dimension(35, height));
		panel_WEST.add(id, BorderLayout.CENTER);

		JLabel line_SOUTH = new JLabel();
		line_SOUTH.setPreferredSize(new Dimension(Integer.MAX_VALUE, 1));
		line_SOUTH.setBackground(new Color(220, 220, 220));
		line_SOUTH.setOpaque(true);
		add(line_SOUTH, BorderLayout.SOUTH);

		panel_CENTER = new JPanel();
		panel_CENTER.setOpaque(false);
		add(panel_CENTER, BorderLayout.CENTER);
		panel_CENTER.setLayout(new BorderLayout(0, 0));

		panel_CENTER_CENTE = new JPanel();
		panel_CENTER_CENTE.setOpaque(false);
		panel_CENTER.add(panel_CENTER_CENTE, BorderLayout.CENTER);
		panel_CENTER_CENTE.setLayout(new GridLayout(1, 0, 0, 0));

		id.setFont(new Font("Tahoma", Font.BOLD, 12));

		JPanel panel_EAST = new JPanel();
		panel_EAST.setBorder(new EmptyBorder(0, 0, 0, 10));
		panel_EAST.setOpaque(false);

		if (!father.father.equals("sincrorepuestos_search")) {
			if (flag) {
				panel_EAST.setPreferredSize(new Dimension(100, height));
				panel_EAST.setLayout(new GridLayout(0, 4, 0, 0));
			} else {
				panel_EAST.setPreferredSize(new Dimension(50, height));
				panel_EAST.setLayout(new GridLayout(0, 2, 0, 0));
			}
		} else {
			panel_EAST.setPreferredSize(new Dimension(30, height));
			panel_EAST.setLayout(new GridLayout(0, 1, 0, 0));
		}

		add(panel_EAST, BorderLayout.EAST);

		for (int i = 1; i < data.length; i++) {
			addCamp(data[i]);
		}

		if (!father.father.equals("sincrorepuestos_search")) {
			JLabel JL_delete;
			if (data.length > 2) {
				if (data[2].equals("eliminado"))
					JL_delete = new JLabel(new ImageIcon("img/fileback.png"));
				else
					JL_delete = new JLabel(new ImageIcon("img/trash.png"));
			} else {
				JL_delete = new JLabel(new ImageIcon("img/trash.png"));
			}

			JLabel JL_edit = new JLabel(new ImageIcon("img/pencil.png"));
			JL_delete.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (!data[2].equals("eliminado")) {
						if (edit) {
							edit = !edit;
							int i = 1;
							for (JTextComponent ob : info) {
								ob.setText(backup[i]);
								ob.setFont(new Font("Tahoma", Font.PLAIN, 12));
								ob.setEditable(edit);
								i++;
							}

							JL_edit.setIcon(new ImageIcon("img/pencil.png"));
							JL_delete.setIcon(new ImageIcon("img/trash.png"));
						} else {
							doDelete();
						}
					} else
						doEnable();
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					if (!edit)
						setBackground(new Color(230, 230, 230));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					if (!edit)
						setBackground(new Color(255, 255, 255));
				}
			});
			JL_delete.setOpaque(false);
			JL_edit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					edit = !edit;
					if (edit) {
						int i = 0;
						for (JTextComponent ob : info) {
							ob.setFont(new Font("Tahoma", Font.PLAIN, 16));

							if (father.father.equals("sincrorepuestos")) {
								i++;
								if (!(i == 1 || i == 3 || i == 4 || i == 6))
									ob.setEditable(edit);
							} else {
								ob.setEditable(edit);
							}
						}
						JL_edit.setIcon(new ImageIcon("img/tick.png"));
						JL_delete.setIcon(new ImageIcon("img/cross.png"));

					} else {
						int i = 1, a = 0, b = 0;
						JTextComponent temp = null;
						
						
						if (father.father.equals("sincrorepuestos")) {
							int canti = 0;
							int valoru = 0;
							int valordes = 0;
							
							i = 0;
							for (JTextComponent ob : info) {
								i++;
								if(i == 2)
									canti = Integer.parseInt(ob.getText());
								if(i == 3)
									valoru = Integer.parseInt(ob.getText());
								if(i == 4)
									ob.setText("" + canti * valoru);
								if(i == 5)
									valordes = Integer.parseInt(ob.getText());
								if(i == 6)
									ob.setText("" + (canti * valoru - valordes));
							}
							Sincrorepuestos sr = (Sincrorepuestos) jFather;
							sr.calculate();
						}
						
						if (father.father.equals("sincroautos")) {
							Sincroautos sa = (Sincroautos) jFather;
							sa.calculate();
						}
						
						i=1;
						
						
						for (JTextComponent ob : info) {
							ob.setFont(new Font("Tahoma", Font.PLAIN, 12));
							ob.setEditable(edit);
							backup[i] = ob.getText();

							if (father.father.equals("sincrorepuestos")) {
								if (i == 2) {
									a = Integer.parseInt(backup[i]);
								}
								if (i == 3) {
									b = Integer.parseInt(backup[i]);
								}

								if (i == 4) {
									temp = ob;
								}
							}
							i++;
						}

						if (father.father.equals("sincrorepuestos")) {
							temp.setText("" + (a * b - Integer.parseInt(backup[5])));
							backup[4] = temp.getText();
						}

						father.updateRow(index, backup);
						JL_edit.setIcon(new ImageIcon("img/pencil.png"));
						JL_delete.setIcon(new ImageIcon("img/trash.png"));
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					if (!edit)
						setBackground(new Color(230, 230, 230));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					if (!edit)
						setBackground(new Color(255, 255, 255));
				}
			});

			if (flag) {
				JLabel JL_download = new JLabel(new ImageIcon("img/download.png"));
				JL_download.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						if (!edit)
							setBackground(new Color(230, 230, 230));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						if (!edit)
							setBackground(new Color(255, 255, 255));
					}
				});
				JLabel JL_view = new JLabel(new ImageIcon("img/views.png"));
				JL_view.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						if (!edit)
							setBackground(new Color(230, 230, 230));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						if (!edit)
							setBackground(new Color(255, 255, 255));
					}
				});
				panel_EAST.add(JL_view);
				panel_EAST.add(JL_download);
			}

			panel_EAST.add(JL_edit);
			panel_EAST.add(JL_delete);
		} else {
			JLabel JL_target;
			JL_target = new JLabel(new ImageIcon("img/target.png"));
			JL_target.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Sincrorepuestos sr = (Sincrorepuestos) jFather;
					sr.addRow(backup[0]);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					if (!edit)
						setBackground(new Color(230, 230, 230));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					if (!edit)
						setBackground(new Color(255, 255, 255));
				}
			});
			panel_EAST.add(JL_target);
		}
	}
}