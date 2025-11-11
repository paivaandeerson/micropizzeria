<h1> Navegue até a Classe de sufixo <b>DAOImpl</b>, localize o método que realiza a consulta desejada e insira o código abaixo:</h1>

   ```    
    // Início
    System.out.println("Query:");
	System.out.println(sql.toString());
	System.out.println("");
	System.out.println("Params:");
	System.out.println(arrayParaString(parans.toArray(new Object[parans.size()])));
	System.out.println("");
    // Fim

		return queryListPagination(sql.toString(), params.toArray(new Object[params.size()]), ...
   }
   ```