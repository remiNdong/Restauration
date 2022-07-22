package fr.restauration.outils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/*
**Objet qui permettra de diviser une liste de Restaurants en plusieurs listes de 10 restaurants
*Cela permettra d'afficher les restaurants 10 par 10
**/

public class EnsemblePage<E> {

	private List<E> liste;
	private TreeMap<Integer, List<E>> pages;

	public EnsemblePage(List<E> liste) {

		this.liste = liste;
		calculerLesPAges();
	}

	public void calculerLesPAges() {
		pages = new TreeMap<Integer, List<E>>();

		int indice = 1;
		int dizaine = 1;
		List<E> collect = new ArrayList<E>();

		// on divise la liste initiale en liste de 10

		for (int i = 0; i < liste.size(); i++) {

			collect.add(liste.get(i));

			if ((i + 1) % 10 * dizaine == 0) {
				pages.put(indice, collect);
				indice++;
				dizaine++;
				collect = new ArrayList<E>();
			}

		}

		// on enregistre la derniere page que si elle a au moins un objet
		if (pages.get(indice) == null && collect.size() > 0)
			pages.put(indice, collect);

	}

	public List<E> getPage(int index) {

		return pages.get(index);

	}

	public List<E> getListe() {
		return liste;
	}

	public void setListe(List<E> liste) {
		this.liste = liste;
	}

	public TreeMap<Integer, List<E>> getPages() {
		return pages;
	}

	public void setPages(TreeMap<Integer, List<E>> pages) {
		this.pages = pages;
	}
	
	public int taille() {
		return pages.size();
	}

}
