package arreglos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import clases.Cliente;

public class ArregloClientes {
	// Atributo privado
	private ArrayList<Cliente> clientes;
	private String archivo;
	
	// Constructor
	public ArregloClientes(String archivo) {
		clientes = new ArrayList<Cliente>();
		this.archivo = archivo;
		cargarClientes(); // Lectura en el archivo de texto de Clientes
		
		// Clientes de ejemplo
		/*clientes.add(new Cliente(10001, "Julia", "Mendoza", "Ortega", 
				"Jr Los �palos 2052", "25/11/1970", "15/07/2004", 1, 
				"3751245", "08754242", "julia", "mendoza"));
		
		clientes.add(new Cliente(10002, "Mar�a", "Ayala", "Apaza", 
				"Calle Las Amatistas 1411", "25/11/1970", "15/07/2004", 2, 
				"3751245", "08754242", "maria", "ayala"));
		
		clientes.add(new Cliente(10003, "Pedro", "Fernandez", "Marquez", 
				"Av Cabo 111", "25/11/1970", "15/07/2004", 3, 
				"3751245", "08754242", "pedro", "fernandez"));*/
	}
	
	// M�todo de acceso p�blico: set/get
	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	
	// Operaciones p�blicas b�sicas
	public int tama�o() {
		return clientes.size();
	}
	
	public Cliente obtener(int indice) {
		return clientes.get(indice);
	}
	
	public void adicionar(Cliente cliente) {
		clientes.add(cliente);
	}
	
	public void eliminarAlFinal() {
		clientes.remove(tama�o() - 1);
	}
	
	public void eliminarTodo() {
		clientes.clear();
	}
	
	// Operaciones p�blicas complementarias
	public Cliente buscar(int codigo) {
		for (int i = 0; i < tama�o(); i++) {
			if (obtener(i).getCodigo() == codigo) {
				return obtener(i);
			}
		}
		
		return null;
	}
	
	public void eliminar(Cliente cliente) {
		clientes.remove(cliente);
	}
	
	public int codigoCorrelativo() {
		if (tama�o() == 0) {
			return 10001;
		} else {
			return obtener(tama�o() - 1).getCodigo() + 1;
		}
	}
	
	// Se lee del archivo de texto de Clientes
	public void cargarClientes() {
		try {
			BufferedReader br;
			String linea, s[];
			int codigo, estadoCivil;
			String nombres, apellidoPaterno, apellidoMaterno, direccion, fechaNacimiento,
					fechaAfiliacion, telefono, dni, usuario, contrase�a;
			br = new BufferedReader(new FileReader(archivo));
			
			while ((linea = br.readLine()) != null) {
				s = linea.split(";");
				codigo 			= Integer.parseInt(s[0].trim());
				nombres 		= s[1].trim();
				apellidoPaterno = s[2].trim();
				apellidoMaterno = s[3].trim();
				direccion 		= s[4].trim();
				fechaNacimiento = s[5].trim();
				fechaAfiliacion = s[6].trim();
				estadoCivil		= Integer.parseInt(s[7].trim());
				telefono 		= s[8].trim();
				dni		 		= s[9].trim();
				usuario 		= s[10].trim();
				contrase�a 		= s[11].trim();
				adicionar(new Cliente(codigo, nombres, apellidoPaterno, apellidoMaterno, direccion, fechaNacimiento, 
						fechaAfiliacion, estadoCivil, telefono, dni, usuario, contrase�a));
			}
			br.close();
			
		} catch (Exception e) {
			
		}
	}
	
	// Se escribe en el archivo de texto de Clientes
	public void grabarClientes() {
		try {
			PrintWriter pw;
			String linea;
			pw = new PrintWriter(new FileWriter(archivo));
			
			for (Cliente cliente : clientes) {
				linea = cliente.getCodigo() + ";" +
						cliente.getNombres() + ";" + 
						cliente.getApellidoPaterno() + ";" + 
						cliente.getApellidoMaterno() + ";" +
						cliente.getDireccion() + ";" +
						cliente.getFechaNacimiento() + ";" +
						cliente.getFechaAfiliacion() + ";" +
						cliente.getEstadoCivil() + ";" +
						cliente.getTelefono() + ";" +
						cliente.getDni() + ";" +
						cliente.getUsuario() + ";" +
						cliente.getContrase�a();
				pw.println(linea);
			}
			pw.close();
			
		} catch (Exception e) {

		}
	}
	
	// Se consulta la existencia del archivo
	public boolean existeArchivo() {
		File f = new File(archivo);
		return f.exists();
	}
}
