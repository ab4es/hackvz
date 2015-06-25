package simpleGa;

public class Population {

	Individual[] individuals;

	/*
	 * Constructors
	 */
	// Create a population
	public Population(int populationSize, boolean initialize) {
		individuals = new Individual[populationSize];
		// Initialize population
		if (initialize) {
			// Loop and create individuals
			for (int i = 0; i < size(); i++) {
				Individual newIndividual = new Individual();
				newIndividual.generateIndividual();
				saveIndividual(i, newIndividual);
			}
		}
	}

	/* Getters */
	public Individual getIndividual(int index) {
		return individuals[index];
	}

	public Individual getFittest() {
		Individual fittest = individuals[0];
		// Loop through individuals to find fittest
		for (int i = 0; i < size(); i++) {
			if (fittest.getFitness() <= getIndividual(i).getFitness()) {
				fittest = getIndividual(i);
			}
		}
		return fittest;
	}

	/* Public Methods */
	// Get population size
	public int size() {
		return individuals.length;
	}

	public void saveIndividual(int index, Individual indiv) {
		individuals[index] = indiv;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
