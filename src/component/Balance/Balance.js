import React, {useState, useEffect} from 'react';
import { AuthService } from '..';
import { Header } from '../index'

function Balance() {
    const [cbalance, setBalance] = useState(250)
    useEffect(() => {
        balance()
     }, [])
    const balance = () =>{
        AuthService.getBalanceUser().then((e) => 
            {
                let obj = JSON.parse(e)
                console.log(obj.balance)
                setBalance(obj.balance)
            }
        )
    }
  return (
    <div>
        <Header />
        <h1> {cbalance} </h1>
        {/* <button onClick = {()=>{balance()}} ></button> */}
    </div>
  )
}

export default Balance
