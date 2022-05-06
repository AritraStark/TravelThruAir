import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import axios from 'axios'
import {useNavigate} from 'react-router-dom'


export default function AddFl() {
    const [name, setName] = React.useState("");
    const [dept, setDept] = React.useState("");
    const [arrival, setArrival] = React.useState("");
    const [cost, setCost] = React.useState("");
    const [time, setTime] = React.useState(0);
    let navigate = useNavigate();


    const handlePost = () =>{

        console.log('post started')
        var today = new Date();

        const config = {
            headers:{
                'Content-type':'application/json'
            }
          }
          axios.post('/travel/deals',{
              name,
              time,
              cost,
              dept,
              arrival
          } ,config)
              .then((res)=>{
                  console.log(res.data)
                }
              )
              .catch(err=>console.log(err))
              setName("")
      setArrival("")
      setDept("")
      setCost("")
      setTime("")
      navigate('/')
        console.log('posted')
    }

  return (
    <Box
      component="form"
      sx={{
        '& > :not(style)': { my: 4, mx:0, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >
      <TextField id="standard-basic" label="Name" variant="standard" value={name} onChange={(e)=>setName(e.target.value)}/>
      <TextField id="standard-basic" label="Departure" variant="standard" value={dept} onChange={(e)=>setDept(e.target.value)}/>
      <TextField id="standard-basic" label="Arrival" variant="standard" value={arrival} onChange={(e)=>setArrival(e.target.value)}/>
      <TextField id="standard-basic" label="Amount" variant="standard" value={cost} onChange={(e)=>setCost(e.target.value)}/>
      <TextField id="standard-basic" label="Expire At" variant="standard" value={time} onChange={(e)=>setTime(e.target.value)}/>
      <Button variant="contained" onClick={handlePost}>Add Deal</Button>
    </Box>
  );
}
