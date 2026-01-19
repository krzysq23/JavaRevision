package revision.designPatterns.structural;

import org.jsoup.nodes.Attribute;

public class DecoratorLibExample {

    /*
        Przykład wykorzystanie dekoratora w klasie Attribute z biblioteki jsoup.
     */

    public static void main(String[] args) {

//        System.out.println("Example1");
//        Attribute attribute1 = new AttributeBeforeDecorator(new Attribute("key1", "value1"));
//        System.out.println(attribute1.getKey());
//        System.out.println();

        System.out.println("Example2");
        Attribute attribute2 = new AttributeAfterDecorator(new AttributeBeforeDecorator(new Attribute("key2", "value2")));
        System.out.println(attribute2.getKey());
    }

    public abstract static class AttributeDecorator extends Attribute {

        private final Attribute attribute;

        public AttributeDecorator(final Attribute attribute) {
            super(attribute.getKey(), attribute.getValue());
            this.attribute = attribute;
        }

        @Override
        public String getKey() {
            return super.getKey();
        }
    }

    public static class AttributeBeforeDecorator extends AttributeDecorator {

        public AttributeBeforeDecorator(Attribute attribute) {
            super(attribute);
        }

        @Override
        public String getKey() {
            System.out.println("Decorating before getKey()");
            return super.getKey();
        }
    }

    public static class AttributeAfterDecorator extends AttributeDecorator {

        public AttributeAfterDecorator(Attribute attribute) {
            super(attribute);
        }

        @Override
        public String getKey() {
            String key = super.getKey();
            System.out.println("Decorating after getKey()");
            return key;
        }
    }
}
