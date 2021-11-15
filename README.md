# Assignement4
Run Tsppro
 public void TSPPro() throws InterruptedException {
        flag2 = true;
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
