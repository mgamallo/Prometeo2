package es.mgamallo.prometeo;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class BandejasXedoc {

	boolean usuarioGlobal = true;
	
	public ArrayList<Bandeja> listaBandejas = new ArrayList<Bandeja>();
	
	public BandejasXedoc() {
		// TODO Auto-generated constructor stub
				
		System.out.println("Usuarios... " + Inicio.usuarios.length);
		for(int i=0;i<Inicio.usuarios.length;i++){
			System.out.println(Inicio.usuarios[i].alias);
			System.out.println(Inicio.usuarios[i].usuario);
			System.out.println("-----------");
		}
		
		if(!Inicio.usuarioLogeadoWindows.equals("cahcpon")){
			usuarioGlobal = false;
			listaBandejas.add(new Bandeja(Inicio.usuario.alias, getNumPdfs("x:\\")));
		}
		else{
			
			System.out.println("Empezamos bandejas...");
			
			File raiz = new File("x:\\");
			File bandejas[] = raiz.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					// TODO Auto-generated method stub
					return pathname.isDirectory();
				}
			});
			
			for(int i=0;i<bandejas.length;i++){
				System.out.println(bandejas[i].getName());
			}
			
			for(int i=0;i<bandejas.length;i++){
				
				Bandeja band = new Bandeja(bandejas[i]);
				if(band.usuario != null){
					listaBandejas.add(band);
				}
				
			}
		}
		
		
		for(int i=0;i<listaBandejas.size();i++){
			System.out.println(listaBandejas.get(i).usuario + " --- " + listaBandejas.get(i).numPdfs);
		}
		
		
	}

	
	private int getNumPdfs(String ruta){
		File f = new File(ruta);
		File pdfs[] = f.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				return pathname.getName().endsWith(".pdf");
			}
		});
		
		return pdfs.length;
	}
	
	static public void main(String args[]){
		new BandejasXedoc();
	}
}

class Bandeja{
	String usuario;
	int numPdfs = 0;
	
	public Bandeja(String usuario, int numPdfs) {
		// TODO Auto-generated constructor stub
		this.usuario = usuario;
		this.numPdfs = numPdfs;
	}
	
	public Bandeja(File directorio){
		
		for(int i=0;i<Inicio.usuarios.length;i++){
			if(directorio.getName().equals(Inicio.usuarios[i].usuario)){
				usuario = Inicio.usuarios[i].alias;
				break;
			}
		}
		
		if(usuario != null){
			File pdfs[] = directorio.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					// TODO Auto-generated method stub
					return pathname.getName().endsWith(".pdf");
				}
			});
			
			numPdfs = pdfs.length;
		}

		
		
	}
}