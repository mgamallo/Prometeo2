package es.mgamallo.prometeo;

public class TemporalParaBorrar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			LeerExcel excel = new LeerExcel();
			excel.leer("documentos.xls");
			
			for(int i=0;i<excel.tablaNormas.length;i++){
				String ruta = "j:/txt/N " + "00" + (i+1) + ".txt";
				Txt.escribirNormasTxt(ruta, excel.tablaNormas[i]);
			}
			
	}

}
