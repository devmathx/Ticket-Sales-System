package interfaces;

import java.util.ArrayList;

import services.FileTXT;

/**
 * Padronização dos controllers
 */
public interface Controller<T> {

  /**
   * Lista de dados
   */
  public ArrayList<Object> clients = new ArrayList<>();
  public final FileTXT TXT = new FileTXT(null);

  /**
   * Busca por um obejto
   * @param param Chave de busca
   * @return Objeto encontrado
   */
  public T find(String param);

  /**
   * Remove um objeto, caso encontre
   * @param param Chave de busca
   * @return Variável de sucesso
   */
  public boolean remove(String param);
  
  /**
   * Retorna a lista referente ao Controller
   * @return Lista de objetos
   */
  public ArrayList<T> getList();
} 
