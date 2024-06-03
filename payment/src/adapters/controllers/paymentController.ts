import express, { Request, Response } from 'express';
import { PaymentService } from '../../domain/services/paymentService';
import { PaymentRepository } from '../repositories/paymentRepository';
import { AdyenService } from '../services/adyenService';

const router = express.Router();

const paymentRepository = new PaymentRepository();
const adyenService = new AdyenService();
const paymentService = new PaymentService(paymentRepository, adyenService);

// Health Check Endpoint
router.get('/health', (req: Request, res: Response) => {
    res.status(200).send({ status: 'OK', message: 'Payment service is up and running!' });
  });
  
router.post('/initiatePayment', async (req: Request, res: Response) => {
  const paymentDetails = req.body;
  
  try {
    const response = await paymentService.initiatePayment(paymentDetails);
    
    switch (response.resultCode) {
      case "Authorised":
        res.redirect('/result/success');
        break;
      case "Pending":
      case "Received":
        res.redirect('/result/pending');
        break;
      case "Refused":
        res.redirect('/result/failed');
        break;
      default:
        res.redirect('/result/error');
        break;
    }
  } catch (error) {
    res.status(500).send('An error occurred while processing the payment');
  }
});

export default router;
