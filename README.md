# Assignement4
 public void TSPPro() throws InterruptedException {
        TSPPro tspPro = new TSPPro("thread");
        tspPro.setCityList(cities);
        tspPro.run();
        routeList = tspPro.getRouteList();
        for (Route value : routeList) {
            System.out.println(value.getSrc().getLabel() + "----->" + value.getDest().getLabel());
        }
        System.out.println(tspPro.getShortestDistance());
        repaint();
    }
