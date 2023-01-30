package Core.MVP;

import Core.Models.Toy;
import UI.ConsoleView;

public class Presenter {
    private final Model model;
    private final View view;

    public Presenter(View view, String pathDb) {
        this.view = view;
        model = new Model(pathDb);
    }

    public void LoadFromFile() {
        model.load();
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
}
