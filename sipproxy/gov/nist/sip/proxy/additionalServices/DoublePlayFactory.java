package gov.nist.sip.proxy.additionalServices;

public class DoublePlayFactory implements ChargerFactory{

	@Override
	public Charger createCharger() {
		return new DoublePlayCharger();
	}

}
