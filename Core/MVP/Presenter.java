package Core.MVP;

import Core.Models.Toy;
import UI.ConsoleView;

import java.util.PriorityQueue;

public class Presenter {
    private final Model model;
    private final View view;

    public Presenter(View view, String pathDb) {
        this.view = view;
        model = new Model(pathDb);
    }

    public void loadFromFile() {
        model.load();
        view.loadMessage();
    }

    public void putForDrawing() {
        if (model.currentDrawingService().putForDrawing(
                new Toy(view.getToyId(), view.getToyNaming(), view.getToyWeight())))
            view.savedItem();
        else
            view.saveError();
    }

    public void deleteFromDrawing() {
        if (model.currentDrawingService.getToys().size() == 0)
            view.emptyListMessage();
        else
            model.currentDrawingService().remove(view.getToyId());
    }

    public void saveToFile() {
        model.save();
        view.savedAll();
    }

    public void showAll() {
        if (model.currentDrawingService.getToys().size() == 0)
            view.emptyListMessage();
        else
            view.showAll(model.currentDrawingService.getToys());
    }

    public void clearAll() {
        if (model.currentDrawingService.getToys().size() == 0)
            view.emptyListMessage();
        else {
            if (view.clearAllDecision()) {
                model.currentDrawingService.getToys().clear();
                System.out.println("All entries are cleaned!");
            }
        }
    }

    public void getDrawing() {
        PriorityQueue<Toy> priorityQueue = new PriorityQueue<>();
        Toy drawnToy;
        priorityQueue.addAll(model.currentDrawingService().getToys());
        if (priorityQueue.size() != 0) {
            drawnToy = priorityQueue.remove();
            model.currentDrawingService().remove(drawnToy.getId());
            view.showGetToy(drawnToy);
        } else
            view.emptyListMessage();

    }

    public void getDrawingWithoutRemoving() {
        PriorityQueue<Toy> priorityQueue = new PriorityQueue<>();
        Toy drawnToy;
        if (model.currentDrawingService.getToys().size() != 0){
            int times = view.getDrawTimes();
            priorityQueue.addAll(model.currentDrawingService().getToys());
            for (int i=0; i < times; i++){
                if (priorityQueue.size() == 0) {
                    priorityQueue.addAll(model.currentDrawingService().getToys());
                }
                drawnToy = priorityQueue.remove();
                view.showGetToy(drawnToy);
            }
        }
        else
            view.emptyListMessage();


    }
}
