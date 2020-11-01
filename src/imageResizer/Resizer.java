package imageResizer;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Vector;

import javax.imageio.ImageIO;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.Popup;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;

import org.eclipse.swt.widgets.FileDialog;

import chrriis.dj.nativeswing.swtimpl.components.JDirectoryDialog;
import chrriis.dj.nativeswing.swtimpl.components.JFileDialog;
import chrriis.dj.nativeswing.swtimpl.components.JFileDialog.DialogType;
import chrriis.dj.nativeswing.swtimpl.components.JFileDialog.SelectionMode;

import javax.swing.event.ChangeEvent;
import javax.swing.ImageIcon;

public class Resizer {
	
	
	private static JFileDialog fileDialog= new JFileDialog();
	private static JDirectoryDialog outputdialog = new JDirectoryDialog();
	
	private static JLabel eglabel = new JLabel("e.g :  img##");
	private static JLabel widthlabel = new JLabel("New Width:");
	private static JLabel Hightlabel = new JLabel("New Height:");
	private static BufferedImage originalImage,resizeImageJpg;
	private static int type,selectedIndex ;
	private static  int IMG_WIDTH=0;
	private static  int IMG_HEIGHT=0;
	private static File outputfile;
	
	private static List <File> inputlist = new ArrayList <File>();
	private static String iconpath;
	private static String inputpath= new String();
	private static ImageIcon icon;
	private static String outputpath,name,temp,path,loc,ext= new String();
	private static String [] filespath = new String[1000];
	private JTextField textField;
	private JTextField rename;
	private JTextField inputtextfield;
	public  Boolean flag=false;
	
	
	public Resizer()
	{
		String username = System.getProperty("user.name");
		
		InetAddress address;
		try {
			address = InetAddress.getByName("www.facebook.com");
			System.out.println(address);
			 
			 System.out.println(address.getHostAddress());
		} catch (UnknownHostException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		        fileDialog.setSelectionMode(SelectionMode.MULTIPLE_SELECTION);
		        fileDialog.setDialogType(DialogType.OPEN_DIALOG_TYPE);
		       
		        fileDialog.setExtensionFilters(
		        	    new String[] {"*.jpg;*.jpeg;*.png"},
		        	    new String[] {"Image file (*.jpg,*.jpeg,*.png)"},
		        	    1);
		         ext=".jpg";
		         loc="C:\\Users\\"+username+"\\Desktop\\output folder";
		         File Dir = new File("C:\\Users\\"+username+"\\Desktop\\output folder");
		         DefaultListModel model= new DefaultListModel();
		         JLabel imagelabel = new JLabel("");
	           	
	 			outputpath="";
				inputpath="";
				
				 final JPopupMenu popup = new JPopupMenu();
				 JMenuItem remove= new JMenuItem("Remove");
				 popup.add(remove);
				 
				 remove.setIcon(null);
				 
				 remove.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						
						model.remove(selectedIndex);
						inputlist.remove(selectedIndex);
					    
					}
				});
				 
				final JFrame frame = new JFrame("Image Resizer");
				
				frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files (x86)\\ImageResizer\\images\\1.png"));
				frame.getContentPane().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						JComponent clicked = (JComponent)e.getSource();
				        clicked.requestFocusInWindow();
						 
					}
				});
				frame.getContentPane().setBackground(new Color(0, 128, 128));
				frame.getContentPane().setLayout(null);
				frame.setSize(806, 462);
				JButton selectbutton = new JButton("Select File");
				selectbutton.setForeground(new Color(34, 139, 34));
				selectbutton.setBounds(225, 46, 104, 23);
				frame.getContentPane().add(selectbutton);
				
				JList list = new JList();
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
				JButton OutputFolder = new JButton("Browse");
				OutputFolder.setBounds(660, 92, 104, 23);
				frame.getContentPane().add(OutputFolder);
				
				JButton btnPowerResize = new JButton("Convert");
				btnPowerResize.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						if(outputpath=="")
						{
							if(!Dir.exists())
							{
								 try{
								        Dir.mkdir();
								       
								    } 
								    catch(SecurityException se){
								        se.printStackTrace();
								    }  
								
								
							}
						}
				
						if(flag==true)
						{
							Boolean flag1=false;
							
							if(IMG_HEIGHT==0 && IMG_WIDTH==0)
							{
								flag1=true;
							}
							for(int i=0;i<inputlist.size();i++)
							{
								
								if(outputpath !="" && outputpath !=null)
									loc=outputpath;
								else
									loc="C:\\Users\\"+username+"\\Desktop\\output folder";
								
								path =inputlist.get(i).getAbsolutePath();
								
								
								try {
									originalImage = ImageIO.read(new File(path));
									if(IMG_HEIGHT==0)
										IMG_HEIGHT=originalImage.getHeight();
									if(IMG_WIDTH==0)
										IMG_WIDTH=originalImage.getWidth();
									
									 type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								loc+="\\"+name+(i+1)+ext;
							
								resizeImageJpg = resizeImageWithHint(originalImage, type);
							

								try {
									ImageIO.write(resizeImageJpg, "jpg", new File(loc));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 
								if(flag1==true)
								{
									IMG_HEIGHT=0;
									IMG_WIDTH=0;
								}
								
							}
							
							imagelabel.setIcon(null);
						}
						else
						{
							int count=1;
							
                             Boolean flag1=false;
							
							if(IMG_HEIGHT==0 && IMG_WIDTH==0)
							{
								flag1=true;
							}
							
							for(int i=0;i<inputlist.size();i++)
							{
								if(outputpath !="" && outputpath !=null)
									loc=outputpath;
								else
									loc="C:\\Users\\"+username+"\\Desktop\\output folder";
								    
								path =inputlist.get(i).getAbsolutePath();
								
								name=inputlist.get(i).getName();
								
								try {
									originalImage = ImageIO.read(new File(path));
									
									if(IMG_HEIGHT==0)
										IMG_HEIGHT=originalImage.getHeight();
									if(IMG_WIDTH==0)
										IMG_WIDTH=originalImage.getWidth();
									
									 type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								loc+="\\"+name;
								
								resizeImageJpg = resizeImageWithHint(originalImage, type);
							

								try {
									Boolean check = new File(loc).exists();
									
									
									if(check)
									{
										while(check)
										{
											int index = loc.indexOf(".");
											String t1,t2;
											t1=loc.substring(0, index);
											t2=loc.substring(index,loc.length());
											
											loc=t1+"_"+count+t2;
											
											 check = new File(loc).exists();
											
											
											count++;
										}
										
										ImageIO.write(resizeImageJpg, "jpg", new File(loc));
									}
									else
									ImageIO.write(resizeImageJpg, "jpg", new File(loc));
									
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 
								
								if(flag1==true)
								{
									IMG_HEIGHT=0;
									IMG_WIDTH=0;
								}
								
							}
							imagelabel.setIcon(null);
						}
					
						
						inputtextfield.setText("");
						inputpath="";
						outputpath="";
						textField.setText("");
						rename.setText("");
						name="";
						inputlist.clear();
						model.clear();
						flag=false;
						
					}
				});
				btnPowerResize.setBounds(429, 319, 104, 23);
				frame.getContentPane().add(btnPowerResize);
				
				textField = new JTextField();
				textField.setForeground(new Color(0, 0, 255));
				textField.setBounds(445, 92, 182, 22);
				frame.getContentPane().add(textField);
				textField.setColumns(10);
				
				textField.setText(loc);
				
				JLabel lblOut = new JLabel("Output Folder");
				lblOut.setForeground(Color.WHITE);
				lblOut.setBounds(341, 96, 86, 14);
				frame.getContentPane().add(lblOut);
				
				JButton btnClose = new JButton("Close");
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						System.exit(0);
					}
				});
				btnClose.setForeground(Color.RED);
				btnClose.setBounds(612, 319, 104, 23);
				frame.getContentPane().add(btnClose);
				
				JCheckBox chckbxRename = new JCheckBox("Batch Rename:");
				chckbxRename.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent ie) {
						if(ie.getStateChange()==1)
						{
							
							rename.setVisible(true);
							eglabel.setVisible(true);
							
						}
						else
						{
							rename.setVisible(false);
							eglabel.setVisible(false);
							
						}
					}
				});
				chckbxRename.setBounds(341, 246, 131, 23);
				frame.getContentPane().add(chckbxRename);
				
				rename = new JTextField(15);
				rename.addFocusListener(new FocusAdapter() {
					@Override
					
					public void focusLost(FocusEvent arg0) {
						name=rename.getText();
						flag=true;
					}
				});
				
				
				
				rename.setVisible(false);
				rename.setBounds(484, 245, 97, 23);
				frame.getContentPane().add(rename);
				rename.setColumns(10);
				
				
				eglabel.setBounds(494, 280, 79, 14);
				frame.getContentPane().add(eglabel);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(20, 93, 309, 296);
				frame.getContentPane().add(scrollPane);
				
				
				list.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent me) {
						
						list.setSelectedIndex(list.locationToIndex(me.getPoint()));
						
						selectedIndex=list.getSelectedIndex();
						iconpath=inputlist.get(selectedIndex).getAbsolutePath();
						
	                   
						icon= new ImageIcon(iconpath);
						Image img= icon.getImage().getScaledInstance(140, 102,Image.SCALE_SMOOTH);
						ImageIcon imgicon= new ImageIcon(img);
						imagelabel.setIcon(imgicon);
						//imagelabel.setIcon(new ImageIcon(iconpath));
						if(SwingUtilities.isRightMouseButton(me) && list.locationToIndex(me.getPoint())== selectedIndex)
						{
							if(!list.isSelectionEmpty())
							{
								 popup.show(list, me.getX(), me.getY());
							}
						}
						
			            /*if (SwingUtilities.isRightMouseButton(me)    // if right mouse button clicked
			                  && !list.isSelectionEmpty()            // and list selection is not empty
			                  && list.locationToIndex(me.getPoint()) // and clicked point is
			                     == list.getSelectedIndex()) {       //   inside selected item bounds
			               popup.show(list, me.getX(), me.getY());
			               selectedIndex=list.getSelectedIndex();
			            }*/
						
						
						 
						 }
						
			         
				});
				
				list.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, Color.GRAY, null, null));
				scrollPane.setViewportView(list);
				
				
				inputtextfield = new JTextField();
				inputtextfield.setForeground(new Color(50, 205, 50));
				inputtextfield.setBounds(20, 46, 193, 25);
				frame.getContentPane().add(inputtextfield);
				inputtextfield.setColumns(10);
				
				JCheckBox chckbxResize = new JCheckBox("Resize");
				
				chckbxResize.setBounds(341, 186, 104, 18);
				frame.getContentPane().add(chckbxResize);
				
				
				widthlabel.setBounds(386, 165, 115, 16);
				frame.getContentPane().add(widthlabel);
				
				
				Hightlabel.setBounds(386, 206, 90, 16);
				frame.getContentPane().add(Hightlabel);
				
				JSpinner Wspinner = new JSpinner();
				Wspinner.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						IMG_WIDTH =(Integer) Wspinner.getValue();
						
					}
				});
				Wspinner.setBounds(484, 159, 97, 28);
				frame.getContentPane().add(Wspinner);
				
				
				
				JSpinner Hspinner = new JSpinner();
				Hspinner.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						
						IMG_HEIGHT= (Integer)Hspinner.getValue();
						
					}
				});
				Hspinner.setBounds(484, 200, 97, 28);
				frame.getContentPane().add(Hspinner);
				
				JPanel preview = new JPanel();
				preview.setBounds(626, 165, 138, 101);
				frame.getContentPane().add(preview);
				preview.setLayout(null);
				preview.setVisible(false);
				
				imagelabel.setBounds(0, 0, 138, 101);
				preview.add(imagelabel);
				
				JCheckBox chckbxPreview = new JCheckBox("Preview");
				chckbxPreview.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent arg0) {
						
						if(arg0.getStateChange()==1)
						{
							preview.setVisible(true);
						}
						else {
							preview.setVisible(false);
						}
					}
				});
				chckbxPreview.setBounds(626, 127, 104, 18);
				frame.getContentPane().add(chckbxPreview);
				
				
				Hspinner.setVisible(false);
				Wspinner.setVisible(false);
				
				eglabel.setVisible(false);
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.setVisible(true);
					Hightlabel.setVisible(false);
					widthlabel.setVisible(false);
				   
					
					chckbxResize.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							if(e.getStateChange()==1)
							{
								
								Hspinner.setVisible(true);
								Wspinner.setVisible(true);
								Hightlabel.setVisible(true);
								widthlabel.setVisible(true);
							}
							else
							{
								Hspinner.setVisible(false);
								Wspinner.setVisible(false);
								Hightlabel.setVisible(false);
								widthlabel.setVisible(false);
								
							}
						}
					});
				    OutputFolder.addActionListener(new ActionListener() {
				    	
						public void actionPerformed(ActionEvent arg0) {
							
							outputpath="";
							outputdialog.show(frame);
							outputpath=outputdialog.getSelectedDirectory();
							
							textField.setText(outputpath);
							
						}
					});
				    
				
				    selectbutton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							
							fileDialog.show(frame);
							  
						String[] filesname = fileDialog.getSelectedFileNames();	
						String parent= fileDialog.getParentDirectory();
						
						if(parent!=null)
						{
							
							
							for(int i=0;i<filesname.length;i++)
							{
								filespath[i]=parent+"\\"+filesname[i];
								
								inputlist.add(new File(filespath[i]));
								model.addElement(filesname[i]);
								
							}
							
							list.setModel(model);
							
							inputtextfield.setText(parent);
							
						}
							
						}
					});
				    
				   
			}
		
		
	
	
	private static BufferedImage resizeImage(BufferedImage originalImage,int type)
	{
		
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
			
		return resizedImage;
		
		
	}
	
	private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){
		
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();	
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		
		return resizedImage;
	    }	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
