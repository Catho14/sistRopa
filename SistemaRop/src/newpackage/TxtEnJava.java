package newpackage;


/*
 Construir una clase con un metodo de instancia que reciba 3 valores y el metodo regrese el mayor de los tres valores.
Contruir un metodo de clase, son aquello estatico que reciba tres valores, y retorne el valor menor
En un main, crear un objeto y utilizar las clases
 */
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
public class TxtEnJava
{
     //Zona de variables 
     private      int    id;
     private      String descripcion;
     private      double precio;
     private      int    cantidad;   
                  File   FicheroProducto= new File("Inventario del almacen.txt");//Creador del archivo, primer cambio a ropa

                 ArrayList<TxtEnJava> cositas =new ArrayList<TxtEnJava>();
                 TxtEnJava             objeto = null;

    //Constructor para la obtencion de datos y variables
   public TxtEnJava(int id,String descripcion,double precio,int cantidad){
         this.id=id;
         this.descripcion=descripcion;
         this.precio=precio; 
         this.cantidad=cantidad; 
    }             
   public TxtEnJava(){} 
   
   //obtener el valor de las variables
   public  int getId()
   {
      return this.id;
       
    }
   public  String getDescripcion()
   {
      return this.descripcion;
       
    }
   public  double getPrecio()
   {
      return this.precio;
       
    }
   public  int getCantidad()
   {
      return this.cantidad;
       
    }
    
   
    //Modificar variables
    public  int setId(int n)
   {
      return id=n;
       
    }
   public  String setDescripcion(String n)
   {
      return descripcion=n;
       
    }
   public  double setPrecio(double n)
   {
      return precio=n;
       
    }
   public  int setCantidad(int n )
   {
      return cantidad=n ;
       
    }
    
   //Empieza el programa
   
   public void comprobarBd(){
        try
      {
        //crea el fichero de base de datos de productos almacen   
        if(!FicheroProducto.exists())
        {
            FicheroProducto.createNewFile();
            JOptionPane.showMessageDialog(null,"Base de datos de productos creada se insertara el producto");//doc txt creado
        }else{   JOptionPane.showMessageDialog(null,"La base de datos de almacen existe");}//Doc existente
       }catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
     } //metodo para insertar  productos con las variables necesarias 
   public void InsertarProductos(int id,String descripcion,double precio,int cantidad)
      {
         try
      {  //Inicio variable y darle valor
         this.id=id;
         this.descripcion=descripcion;
         this.precio=precio; 
         this.cantidad=cantidad; 
           
        /* 
         * Abro el flujo de escritura, sobre el fichero con codificacion utf8  con la clase BufferedWriter no se que es xd
         */  
        
          BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FicheroProducto,true), "utf-8"));  
          /*Escribe en el fichero la cadena que recibe la funcion.  el string "\r\n" significa salto de linea  y el \t significa tabulacion  */  
         //Guarda los datos en el txt
          Fescribe.write(getId()+"\t"+getDescripcion()+"\t"+getPrecio()+"\t"+getCantidad()+"\r\n");  
           JOptionPane.showMessageDialog(null,"El producto ha sido insertado en la base de datos");           //Cierra el flujo de escritura  y guardado del txt
          Fescribe.close();
          
        }
        catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
      } 
   //Metodo para extraer y meter informacion del txt a las variables par a la muestra de los datos case: Guardado
   public void DetxtAObjeto()
     {
        try
        {
         String linea = null;
         BufferedReader leerFichero = new BufferedReader (new FileReader(FicheroProducto));    
         while( (linea = leerFichero.readLine()) != null)
         {
            //este bucle es para meter todos los datos leidos de archivo de texto separlo en atributos y convertirlos a objeto para poder trabajar con el 
            //en esta parte se separa en tokens pero ojo estos tokens son solo Strings tengo que convertirlos para poder emterlos en el objeto y trabajar con ellos
             // los tokens nos apoya para dividir un string en partes, tipo delimitador, para la sepacion entre columnas
            StringTokenizer mistokens = new StringTokenizer(linea, "\t");
            String           id =  mistokens.nextToken().trim();
            String  descripcion =  mistokens.nextToken().trim();
            String       precio =  mistokens.nextToken().trim();
            String     cantidad =  mistokens.nextToken().trim();
           
            //transformo los tipo de String a los tipos que hace falta int double 
            int    idOperar=Integer.parseInt(id);
            double preciOperar=Double.parseDouble(precio);
            int    cantidadOperar=Integer.parseInt(cantidad);
            
            
            //Se pasa al constructor para que se cree los objetos
            objeto= new TxtEnJava(idOperar,descripcion,preciOperar,cantidadOperar);
            //se mete en el arraylist
            cositas.add(objeto);
            
            }
         leerFichero.close();
       
       }catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
      }     
   public void ActualizarArraList()
   {
      //Este es el ArrayList declarado al inicio "cositas"
      cositas.clear();
      DetxtAObjeto(); //Clase de arriba
   }
   //Clase para mostrar los datos guardados en el txt para imprrimirlo, tipo REPORTE
   //No pude en table, pero en consola si se puede facilmente
      public void MostrarObjetos()
      {
     if( cositas.size()==0){DetxtAObjeto();}
     System.out.println("=================================================LISTA DE ARTICULOS===================================================================================================================================================================");     
     for(TxtEnJava n:cositas)//Arreglo para la impresion de cada dato.... txtenjava es el txt
     {
      System.out.println("El id es:"+n.getId()+"\t"+"La descripcion es:"+n.getDescripcion()+"\t"+"El precio es:"+n.getPrecio()+"\t"+ "La cantidad es:"+ n.getCantidad());
     }  
     System.out.println("==============================================FIN DE LA LISTA DE ARTICULOS==========================================================================================================================================================================");
   }
 
      
      //Aqui se hace todo el bisne del programa por JOptionPane 
   public void modificarFichero()
  {
    try{
       //Se inicia la arraylist para saber si hay doc txt o no, en caso de que no, se crea
       if( cositas.size()==0){DetxtAObjeto();}
             
        int opc=11;//opc para el dowhile y el case
             
        while(opc!=10)
             {
               menu();//Innecesario pero para no confundirse
               opc=Integer.parseInt( JOptionPane.showInputDialog("                                    MENU GENERAL\n1.Agregar dato\n2.Modificar ID\n3.Modificar descripcion\n4.Busqueda por descripcion y "
                       + "modificacion del precio\n5.Busqueda por descripcion y modificacion de existencia\n6.Guardar en la Base de Datos\n7.Borrar Registro\n8.Mostrar datos\n9.Busqueda por ID\n10.Cerrar"));
 
               switch(opc)//Inicio del case
               {
                   case 1: //Insertar producto
                   int    i=Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID"));
                   String    descrip=JOptionPane.showInputDialog("Ingrese descripcion");
                   Double prec=Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio"));
                   int cantida=Integer.parseInt(JOptionPane.showInputDialog("Cantidad existente"));
                              try
      {  
         this.id=i;
         this.descripcion=descrip;
         this.precio=prec; 
         this.cantidad=cantida; 
//Se metio todo el codigo porque funciono a la primera, pero se puede llamar el metodo de InsertarProducto para reduccion de codigo 
          BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FicheroProducto,true), "utf-8"));  
          Fescribe.write(getId()+"\t"+getDescripcion()+"\t"+getPrecio()+"\t"+getCantidad()+"\r\n");  
           JOptionPane.showMessageDialog(null,"El producto ha sido insertado en la base de datos");         
          Fescribe.close();
         cositas.clear();//Reinicia el metodo para meter el dato, sin la necesidad de reiniciar
                      DetxtAObjeto();
          
        }
        catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
                               break;
                    case 2: //Cambiar de id a algun producto
        
                   int    idNumero=Integer.parseInt(JOptionPane.showInputDialog("Introduce ID del producto a cambiar"));
                   int    numero=Integer.parseInt(JOptionPane.showInputDialog("Introducir nuevo ID"));      
                    for(TxtEnJava n:cositas)
                    {
                      if(n.getId()==idNumero){
                          
                        n.id =numero;     
                   JOptionPane.showMessageDialog(null, "ID: "+n.getId()+"\nDescripcion: "+n.getDescripcion()+"\nPrecio: $"+n.getPrecio()+"\nCantidad Existente:"+ n.getCantidad());//Impresion de la modificacion
                        break;
                      }else{ //En caso de no existir
                         JOptionPane.showMessageDialog(null,"El producto no ha sido encontrado");
                                         }  
                    }
                    break;
                    
                    
                    
                    
                    case 3: 
                       //Cambio de descripcion de la bd
                    String  cadena=JOptionPane.showInputDialog("Inserte la descripcion del producto actual");    
                    String cadenaNueva=JOptionPane.showInputDialog("Inserte la nueva descripcion del mismo");         
                    for(TxtEnJava n:cositas)
                    {
                       
                      if(n.getDescripcion().equals(cadena)){ //Iguala y busca la descripcion en BD
                          
                        n.descripcion=cadenaNueva;     
                       JOptionPane.showMessageDialog(null, "ID: "+n.getId()+"\n Descripcion: "+n.getDescripcion()+"\nPrecio: $"+n.getPrecio()+"\nCantidad existente: "+ n.getCantidad());
                        break;
                      }else{
                         JOptionPane.showMessageDialog(null, "El producto no ha sido encontrado."); //Prod no existente
                       }  
                    }                                                                                                 
                    break;
                    //Cambio de precio del producto
                    case 4:
                           cadena=JOptionPane.showInputDialog("Inserte la descripcion del producto ");        
                    double precioNuevo=Double.parseDouble(JOptionPane.showInputDialog("Inserte el precio nuevo"));
                    for(TxtEnJava n:cositas) //se busca con descripcion dntro del arraylist
                    {
                      if(n.getDescripcion().equals(cadena)){
                          
                        n.precio=precioNuevo;     //Valor del precio cambiado
                      JOptionPane.showMessageDialog(null, "ID: "+n.getId()+"\nDescripcion: "+n.getDescripcion()+"\nPrecio: $"+n.getPrecio()+"\nCantidad existente:"+ n.getCantidad());
                          break;
                      }else{
                           JOptionPane.showMessageDialog(null,"el producto no ha sido encontrado");//Prod no existente
                      }  
                    }
                    break;
                    
                    case 5: 
                   //Cambio de cantidad de un producto
                           cadena=JOptionPane.showInputDialog("Inserte la descripcion ");       
                          numero=Integer.parseInt(JOptionPane.showInputDialog("Inserte la cantidad nueva "));
                    for(TxtEnJava n:cositas)
                    {
                       
                      if(n.getDescripcion().equals(cadena)){
                          
                        n.cantidad =numero;     
                      JOptionPane.showMessageDialog(null,"ID:"+n.getId()+"\t"+"Descripcion:"+n.getDescripcion()+"\t"+"Precio:  $"+n.getPrecio()+"\t"+ "Cantidad existente:"+ n.getCantidad());
                        break;
                      }else{
                      JOptionPane.showMessageDialog(null,"El producto no ha sido encontrado");
                      }  
                    }                   
                    break;
                    case 6: JOptionPane.showMessageDialog(null, "Guardado en la Base de Datos");//Guardar BD, no necesario pero chido
                    try{
                      BufferedWriter bw = new BufferedWriter(new FileWriter(FicheroProducto));
                      for(TxtEnJava n:cositas)
                      {
                          bw.write(n.id+"\t"+n.descripcion+"\t"+ n.precio+"\t"+ n.cantidad+"\r\n");  //Mete los datos  
                      }
                      bw.close();
                     }catch (Exception ex) 
                    {  
                      //Captura un posible error le imprime en pantalla   
                      System.out.println(ex.getMessage());  
                    }
                    
                    break;  
                    
                    //Para borrar un prod por medio del id
                    case 7: 
                            int v= Integer.parseInt(JOptionPane.showInputDialog( "Inserte  ID del producto que desea borrar de la Base de Datos"));
                            
                    try{
                      BufferedWriter bw = new BufferedWriter(new FileWriter(FicheroProducto));
                      for(TxtEnJava n:cositas) //Arreglo para la impresion de cada dato.... txtenjava es el txt
                      {  
                          if(n.getId()!=v){
                           bw.write(n.id+ "\t"+n.descripcion+ "\t"+ n.precio+ "\t"+ n.cantidad+"\r\n");
                        }else{
                          JOptionPane.showMessageDialog(null, "El producto ha sido eliminado.");
                                         }
                      }
                      bw.close(); //cerrar leidad del doc
                      cositas.clear(); //guardar
                      DetxtAObjeto();
                      
                     }catch (Exception ex) 
                    {  
                      //Captura un posible error le imprime en pantalla   
                      System.out.println(ex.getMessage());  
                    }
                    
                    break;  
                    case 8: //Mostrar datos por consola
                        MostrarObjetos();
                    
                        
                        
                        break;
                        
                        case 9: //Busqueda por id
                      int    iNumero=Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID del producto a buscar"));
                      
                    for(TxtEnJava n:cositas)
                    {
                       
                      if(n.getId()==iNumero){
                        JOptionPane.showMessageDialog(null, "ID: "+n.getId()+"\nDescripcion: "+n.getDescripcion()+"\nPrecio:  $"+n.getPrecio()+"\nCantidad Existente de productos: "+ n.getCantidad());
                        break;
                      }else{
                         //JOptionPane.showMessageDialog(null,"el producto no ha sido encontrado");
                                         }  
                    } break;    
                }
               
            } 
        } catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }     
     }  
  
  private void menu()
   {
    
     
   }
    //Main   
  public  void main(String args[]) 
   {
       
     comprobarBd();
     
  //   InsertarProductos(11,"Czon",170,700);
  //   MostrarObjetos();
     modificarFichero();
     
    } 
}
