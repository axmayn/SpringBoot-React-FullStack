import {Text, CircularProgress, Wrap, WrapItem } from '@chakra-ui/react'
import SidebarWithHeader from './component/shared/Sidebar.jsx';
import { getCustomers } from './services/client.js';
import { useEffect, useState } from 'react';
import Card from './component/Card.jsx';


function App() {

  const [customers, setCustomers] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);

    getCustomers().then(
      res => setCustomers(res.data))
      .catch(err =>
      console.log(err))
      .finally(() =>
      setLoading(false));
  }, [])

  if(loading){
    return (
      <SidebarWithHeader> 
          <CircularProgress isIndeterminate value={80} />
      </SidebarWithHeader>
    );
  }
  else if(customers.length <= 0){
    return (
      <SidebarWithHeader> 
          <Text>No Customer to show</Text>
      </SidebarWithHeader>
    );
  }


  

  return (
    <SidebarWithHeader> 
        <Wrap justify="center" spacing='20'>
        {customers.map( customer =>
            <WrapItem>
              <Card name={customer.name} email={customer.email}></Card>
            </WrapItem>
              
        )}
        </Wrap>

    </SidebarWithHeader>
  );
  
}

export default App;
