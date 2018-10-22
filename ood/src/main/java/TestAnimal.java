class Animal {
    public void say() {
        System.out.println("...");
    }
}

class Cat extends Animal {
    @Override
    public void say() {
        System.out.println("喵");
    }
}

class Dog extends Animal {
    @Override
    public void say() {
        System.out.println("汪");
    }
}

/**
 *
 */
public class TestAnimal {
    /**
     * Overload 重载
     *
     * @param c
     */
    public void say(Cat c) {
        System.out.print("猫：");
        c.say();
    }

    /**
     * Overload 重载
     *
     * @param d
     */
    public void say(Dog d) {
        System.out.print("狗：");
        d.say();
    }

    /**
     * Overload 重载
     *
     * @param a
     */
    public void say(Animal a) {
        System.out.print("动物：");
        a.say();
    }

    public static void main(String[] args) {
        Animal a0 = new Animal();
        Animal a1 = new Cat();
        TestAnimal test = new TestAnimal();
        test.say(a1);

        // up-casting （向上转型)
        Animal a2 = new Dog();
        // down-casting (向下转型)
        test.say((Dog)a2);

        a0.say();
        a1.say();
    }
}