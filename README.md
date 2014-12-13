SigEvent
========

event android like eventbus
类似于eventbus 的事件处理机制，最大的特点是可以控制接收方，是群发还是定点发送，是否在主线程执行，这是跟eventbus 最大的区别。
而且使用注解方式使用方便。
注册：
EventManager.init().regedit(this);
1.普通发送 像eventbus 一样可以自带bean 发送给接收方，并且可以带事件类型。
发送：
		EventManager.init().sentEvent(new Event());
		EventManager.init().sentEvent(new NetEvent("callback"));
		
接收：

	@EventType
	public void test(Event event) {
		System.out.println("执行test");
	}

	@EventType("callback")
	public void test2(NetEvent event) {
		System.out.println("执行test2");
	}
	
	2.带固定接收方发送有些时候很多类都监听了这一类型的事件，但是只想固定给某个类发送整个消息。
	发送：
	EventTestModel eventTestModel= new  EventTestModel();
	EventManager.init().sentEvent(new NetEvent(),eventTestModel);
	
	接收：
	public class EventTestModel implements EventListener {
	public EventTestModel() {
		EventManager.init().regedit(this);
	}

	@EventType
	public void test2(NetEvent event) {
		System.out.println("执行");
	}
}

3.控制接收方的方法在主线程或新开线程执行。
//在UI线程执行
EventManager.init().sentEvent(new NetEvent("callback",ThreadType.UI),eventTestModel);
//在新的线程执行
EventManager.init().sentEvent(new NetEvent("callback",ThreadType.NewThread),eventTestModel);
//跟发送方同一线程
EventManager.init().sentEvent(new NetEvent("callback",ThreadType.LikeSent),eventTestModel);
	
	
