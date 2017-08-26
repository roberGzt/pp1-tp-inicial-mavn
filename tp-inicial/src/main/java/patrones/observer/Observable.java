package patrones.observer;

public interface Observable {
	public void registrarObservador(Observador o);
	public void notificarObservadores();

}
