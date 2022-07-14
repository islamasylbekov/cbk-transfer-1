package kg.cbk.table.core;

public class ActionButton extends Button {

    private static final long serialVersionUID = 9086761498987395813L;

    public static ActionButton of(ActionButtonType type, String title, String url, boolean dialog) {
        return new ActionButton()
                .setButtonType(type)
                .setTitle(title)
                .setActionUrl(url)
                .setDialog(dialog);
    }

    protected ActionButtonType buttonType = ActionButtonType.function;

    protected String method = "get";

    protected boolean needSelected = true;

    protected boolean dialog = false;

    public ActionButton() {
    }

    public boolean isDialog() {
        return dialog;
    }

    public ActionButton setDialog(boolean dialog) {
        this.dialog = dialog;
        return this;
    }

    public boolean isNeedSelected() {
        return needSelected;
    }

    public ActionButton setNeedSelected(boolean needSelected) {
        this.needSelected = needSelected;
        return this;
    }

    public ActionButtonType getButtonType() {
        return buttonType;
    }

    public ActionButton setButtonType(ActionButtonType buttonType) {
        this.buttonType = buttonType;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public ActionButton setMethod(String method) {
        this.method = method;
        return this;
    }

    @Override
    public ActionButton setTitle(String title) {
        super.setTitle(title);
        return this;
    }

    @Override
    public ActionButton setActionUrl(String actionUrl) {
        super.setActionUrl(actionUrl);
        return this;
    }

    @Override
    public String text() {
        return null != icon ?
                String.format(
                        "<i class=\"icon-%s position-left\"></i>%s", icon, getTitle()
                ) :
                getTitle();
    }

    public enum ActionButtonType {
        function, // Ajax
        action; // Direct action

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
