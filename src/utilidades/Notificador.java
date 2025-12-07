package utilidades;

import java.util.List;
import modulos.Tarea;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Notificador extends Thread { // 1. Heredamos de Thread
    
    private List<Tarea> tareasAMonitorear;
    private boolean ejecutando = true;

    public Notificador(List<Tarea> tareas) {
        this.tareasAMonitorear = tareas;
    }

    public void detener() {
        this.ejecutando = false;
    }

    @Override
    public void run() {
        System.out.println("--- Hilo Notificador iniciado ---\n>");
        
        while (ejecutando) {
            try {
                LocalDate hoy = LocalDate.now();
                
                // Revisamos la lista de tareas
                if (tareasAMonitorear != null && !tareasAMonitorear.isEmpty()) {
                    for (Tarea t : tareasAMonitorear) {
                        // Si la tarea está pendiente y vence en menos de 2 días
                        if (t.getEstado().equals("pendiente") && t.getFechaLimite() != null) {
                            long diasRestantes = ChronoUnit.DAYS.between(hoy, t.getFechaLimite());
                            
                            if (diasRestantes >= 0 && diasRestantes <= 1) {
                                System.out.println("\n[ALERTA AUTOMÁTICA]: La tarea '" + t.getTitulo() + 
                                                   "' vence en " + diasRestantes + " días.");
                            }
                        }
                    }
                }

                // Pausamos el hilo 10 segundos para no saturar la CPU
                // En un caso real serían horas, pero para la demo usa segundos.
                Thread.sleep(10000); 

            } catch (InterruptedException e) {
                System.out.println("Notificador interrumpido.");
            }
        }
    }
}