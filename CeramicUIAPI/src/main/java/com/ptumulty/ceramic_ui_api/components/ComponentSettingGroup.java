package com.ptumulty.ceramic_ui_api.components;

import com.ptumulty.ceramic_ui_api.utility.FxUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.util.List;

public class ComponentSettingGroup
{
    private final BorderPane borderPane;
    private final int LABEL_SPACING = 150;
    private final VBox vBox;
    private final Label groupLabel;

    public ComponentSettingGroup(String label, List<UIComponent<?, ?>> components)
    {
        borderPane = new BorderPane();

        groupLabel = new Label(label);
        borderPane.setTop(groupLabel);
        BorderPane.setAlignment(groupLabel, Pos.CENTER_LEFT);
        BorderPane.setMargin(groupLabel, new Insets(0, 0, 10, 10));

        vBox = new VBox();
        vBox.minWidthProperty().bind(borderPane.widthProperty());
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);
        addComponents(components);
        borderPane.setCenter(vBox);
        BorderPane.setAlignment(vBox, Pos.TOP_CENTER);
        BorderPane.setMargin(vBox, new Insets(0, 0, 10, 0));
    }

    public void showTitle(boolean showTitle)
    {
        FxUtils.run(() -> borderPane.setTop(showTitle ? groupLabel : null));
    }

    private void addComponents(List<UIComponent<?, ?>> components)
    {
        for (UIComponent<?, ?> component : components)
        {
            HBox lineItem = new HBox();
            lineItem.setSpacing(10);
            lineItem.setAlignment(Pos.CENTER_LEFT);

            if (component.getLabel().isPresent())
            {
                Label settingLabel = new Label(component.getLabel().get());
                settingLabel.setAlignment(Pos.CENTER_RIGHT);
                settingLabel.setTextAlignment(TextAlignment.RIGHT);
                settingLabel.setMinWidth(LABEL_SPACING);

                lineItem.getChildren().add(settingLabel);
            }

            lineItem.getChildren().add(component.getRenderer());

            if (component.getLabel().isEmpty())
            {
                HBox.setMargin(component.getRenderer(), new Insets(0, 0, 0, LABEL_SPACING + 10));
            }

            vBox.getChildren().add(lineItem);
        }
    }

    public Pane getRenderer()
    {
        return borderPane;
    }
}